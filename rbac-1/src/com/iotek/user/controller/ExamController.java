package com.iotek.user.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.user.po.AjaxResult;
import com.iotek.user.po.Category;
import com.iotek.user.po.ErrorQues;
import com.iotek.user.po.QuesType;
import com.iotek.user.po.Question;
import com.iotek.user.po.Role;
import com.iotek.user.po.Test;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.ErrorQuesService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;
import com.iotek.user.service.TestService;
import com.iotek.util.MyUtils;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping(value = "exam")
public class ExamController {

	@Autowired
	private QuesTypeService quesTypeService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private ErrorQuesService errorQuesService;

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
		PageHelper.startPage(pn, 5);
		List<QuesType> quesTypes = this.quesTypeService.queryAllQuesType();
		// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
		// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
		PageInfo page = new PageInfo(quesTypes, 6);
		model.addAttribute("pageInfo", page);
		return "exam/index";
	}

	@RequestMapping("/toExamPage")
	public String toExamPage(int quesTypeId, Model model) {
		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
		model.addAttribute("quesType", quesType);

		List<Question> questions = this.questionService.queryTenRanQuestionByType(quesTypeId);
		// System.err.println("toExamPage quesTypeId="+quesTypeId);
		List<Integer> quesIds = new ArrayList<>();
//		List<String> answers = new ArrayList<>();
		StringBuilder answers = new StringBuilder();
//		StringBuilder quesIds = new StringBuilder();
		for (Question question : questions) {
			answers.append(question.getQuesAnswer());
			quesIds.add(question.getQuesId());
		}
//		System.err.println("answers.toString()="+quesIds.);
		System.err.println("toExamPage quesIds="+quesIds);
		model.addAttribute("quesIds", quesIds);
		model.addAttribute("questions", questions);
		model.addAttribute("answers", answers.toString());
		return "exam/examPage";
	}
	
	
//	@ResponseBody
	@RequestMapping("/checkSubmitedAnswer")
	public String checkSubmitedAnswer(String submitedAnswer, String answers, 
			String quesIds, String quesTypeName, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.err.println("checkSubmitedAnswer�û��ύ�Ĵ𰸣�submitedAnswer=" + submitedAnswer);
		System.err.println("checkSubmitedAnswer��ȷ�𰸣�answers=" + answers);
		//�Ƚ��ַ���ת�����ַ����飬��ת�����ַ�����
		System.err.println("checkSubmitedAnswer quesIds="+quesIds);
		
		//[1, 2, 3, 6, 5, 6, 7, 8, 9, 10]
		quesIds = quesIds.substring(1);//ɾ����һ���ַ�
		quesIds = quesIds.substring(0, quesIds.length()-1);//ɾ�����һ���ַ�
		String[] quedIdsArrStrs = quesIds.split(", ");
		List<Integer> quedIdsArrInt = new ArrayList<>();
		for (String quedIdsArrStr : quedIdsArrStrs) {
			quedIdsArrInt.add(Integer.valueOf(quedIdsArrStr));
		}
		
//		List<String> submitedAnswerArr = new ArrayList<>();
//		List<String> answerArr = new ArrayList<>();
//		String[] submitedAnswerStr = submitedAnswer.split("");
//		String[] answersStr = answers.split("");
//		for (String string : submitedAnswerStr) {
//			submitedAnswerArr.add(string);
//		}
//		for (String string : answersStr) {
//			answerArr.add(string);
//		}
		List<String> submitedAnswerArr = MyUtils.strsToList(submitedAnswer);
		List<String> answerArr = MyUtils.strsToList(answers);
		
		List<Integer> errorQuesIds = new ArrayList<>();
		Map<Integer, String> errorQuesMap = new HashMap<>();//����ö�Ӧ�����
		int correctNum = 0;
		for (int i = 0; i < answerArr.size(); i++) {
			if (submitedAnswerArr.get(i).equals(answerArr.get(i))) {
				correctNum++;
			} else {
				//����
				errorQuesIds.add(quedIdsArrInt.get(i));
				System.err.println("������Ŀ��ţ�" + quedIdsArrInt.get(i)
						+ ", ����𰸣�" + submitedAnswerArr.get(i));
				errorQuesMap.put(quedIdsArrInt.get(i), submitedAnswerArr.get(i));
			}
		}
		this.addErrorQues(errorQuesMap, user.getUserId());
		
		List<Question> questions = this.questionService.queryQuestionByIds(quedIdsArrInt);
		for(int i = 0; i < questions.size(); i++) {
			questions.get(i).setSubmitAnswer(submitedAnswerArr.get(i));
		}
		
		System.err.println("��������errorQuesIds="+errorQuesIds);
		System.err.println("correctNum="+correctNum);
		
		model.addAttribute("cores", correctNum*10);
		model.addAttribute("questions", questions);
		model.addAttribute("quesTypeName", quesTypeName);
		//���Ծ����ݴ���Ծ����
		
		System.err.println("quesTypeName="+quesTypeName);
		Test test = new Test(quesTypeName, correctNum*10, quesIds, submitedAnswer, user.getUserId(), new Date());
		System.err.println("checkSubmitedAnswer test = " + test.toString());
		int insertResult = this.testService.insertTest(test);  
		System.err.println("checkSubmitedAnswer insertTest="+insertResult);
		
		return "exam/examResult";
	}
	
	
	
	public void addErrorQues(Map<Integer, String> errorQuesMap, int userId) {
		List<ErrorQues> errorQuess = new ArrayList<>();
		Timestamp timestamp = MyUtils.getCurrentTimeStamp();
		for (Integer quesId : errorQuesMap.keySet()) {
			System.err.println(quesId + "--->" + errorQuesMap.get(quesId));
			ErrorQues errorQues = new ErrorQues(quesId, errorQuesMap.get(quesId), userId, timestamp);
			errorQuess.add(errorQues);
		}
		this.errorQuesService.insertErrorQues(errorQuess);
	}
	
	
	
	
	/**
	 * ����
	 * @return
	 */
//	@RequestMapping("/checkSubmitedAnswerTest")
//	public String checkSubmitedAnswerTest(String test1, String test2,
//			String test3, String test4,String test5, String test6,
//			String test7, String test8,String test9, String test10) {
//		System.err.println("test1="+test1);
//		System.err.println("test2="+test2);
//		System.err.println("test3="+test3);
//		System.err.println("test4="+test4);
//		System.err.println("test5="+test5);
//		return "exam/index";
//	}
	
	
	
	

//	@ResponseBody
//	@RequestMapping(value = "insertQuesType")
//	public Object insertQuesType(QuesType quesType, HttpSession session) {
//		User creator = (User) session.getAttribute("user");
//		quesType.setCreator(creator.getUserId());
//		quesType.setCreateDate(new Date());
//		int insertResult = this.quesTypeService.insertQuesType(quesType);
//		AjaxResult result = new AjaxResult();
//		if (insertResult > 0) {
//			result.setSuccess(true);
//		} else {
//			result.setSuccess(false);
//		}
//		return result;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "deleteQuesTypeById")
//	public Object deleteQuesTypeById(Integer quesTypeId) {
//		int deleteResult = this.quesTypeService.deleteQuesTypeById(quesTypeId);
//
//		// ��ɾ����������ʱ��ҲҪɾ��question���и��������͵�����
//		System.err.println("deleteQuesTypeById quesTypeId=" + quesTypeId);
//		List<Integer> quesTypeIds = this.questionService.queryIdByQuesTypeId(quesTypeId);
//		System.err.println("deleteQuesTypeById quesTypeIds=" + quesTypeIds);
//		this.questionService.deleteQuestionByQuesTypeId(quesTypeIds);
//
//		AjaxResult result = new AjaxResult();
//		if (deleteResult > 0) {
//			result.setSuccess(true);
//		} else {
//			result.setSuccess(false);
//		}
//		return result;
//	}
//
//	@RequestMapping("/toUpdateQuesType")
//	public String toUpdateQuesType(int quesTypeId, Model model) {
//		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
//		model.addAttribute("quesType", quesType);
//		return "quesType/updateQuesType";
//	}

//	@ResponseBody
//	@RequestMapping(value = "updateQuesTypeIdById")
//	public Object updatequesTypeIdById(QuesType quesType, HttpSession session) {
//		User updater = (User) session.getAttribute("user");
//		quesType.setUpdater(updater.getUserId());
//		quesType.setUpdateDate(new Date());
//		int udpateResult = this.quesTypeService.updateQuesTypeById(quesType);
//		AjaxResult result = new AjaxResult();
//		if (udpateResult > 0) {
//			result.setSuccess(true);
//		} else {
//			result.setSuccess(false);
//		}
//		return result;
//	}

//	@RequestMapping("/toQuesManage")
//	public String toQuesManage(Integer quesTypeId, Model model, RedirectAttributes redirectAttributes) {
//		// QuesType quesType =
//		// this.quesTypeService.queryQuesTypeById(quesTypeId);
//		// model.addAttribute("quesType", quesType);
//		// return "question/quesManage";
//		System.err.println("toQuesManage  quesTypeId = " + quesTypeId);
//		redirectAttributes.addAttribute("quesTypeId", quesTypeId);
//		return "redirect:/question/index?quesTypeId" + quesTypeId;
//	}
}
