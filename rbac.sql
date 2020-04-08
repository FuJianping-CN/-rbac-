/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-29 14:59:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_auth`;
CREATE TABLE `tb_auth` (
  `authId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `authName` varchar(20) DEFAULT NULL,
  `authUrl` varchar(20) DEFAULT NULL,
  `authParentRoot` int(8) DEFAULT NULL,
  `authIsRoot` varchar(2) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_auth
-- ----------------------------
INSERT INTO `tb_auth` VALUES ('1', '系统菜单', null, '0', '是', '1', '2020-03-20', null, null);
INSERT INTO `tb_auth` VALUES ('2', '控制面板', '', '1', '是', '1', '2020-03-20', null, null);
INSERT INTO `tb_auth` VALUES ('3', '权限管理', '/permission/index', '1', '是', '1', '2020-03-20', null, null);
INSERT INTO `tb_auth` VALUES ('4', '用户维护', '/user/index', '3', '否', '1', '2020-03-20', null, null);
INSERT INTO `tb_auth` VALUES ('5', '角色维护', '/role/index', '3', '否', '1', '2020-03-20', null, null);
INSERT INTO `tb_auth` VALUES ('6', '权限维护', '/permission/index', '3', '否', '1', '2020-03-20', '1', '2020-03-26');
INSERT INTO `tb_auth` VALUES ('7', '新闻中心', '', '1', '是', '1', '2020-03-20', '1', '2020-03-21');
INSERT INTO `tb_auth` VALUES ('8', '栏目管理', '/category/index', '7', '否', '1', '2020-03-20', '1', '2020-03-21');
INSERT INTO `tb_auth` VALUES ('9', '新闻管理', '/news/index', '7', '否', '1', '2020-03-20', '1', '2020-03-21');
INSERT INTO `tb_auth` VALUES ('11', '考试中心', '', '1', '是', '1', '2020-03-21', null, null);
INSERT INTO `tb_auth` VALUES ('20', '题库管理', '/quesType/index', '11', '否', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_auth` VALUES ('21', '在线考试', '/exam/index', '11', '否', '1', '2020-03-23', '1', '2020-03-24');
INSERT INTO `tb_auth` VALUES ('23', '我收藏的试题', '/quesCollect/index', '11', '否', '1', '2020-03-24', '1', '2020-03-24');
INSERT INTO `tb_auth` VALUES ('24', '我的考试记录', '/test/index', '11', '否', '1', '2020-03-24', null, null);
INSERT INTO `tb_auth` VALUES ('25', '我的错题', '/errorQues/index', '11', '否', '1', '2020-03-25', null, null);
INSERT INTO `tb_auth` VALUES ('27', '个人中心', '', '1', '是', '1', '2020-03-27', null, null);
INSERT INTO `tb_auth` VALUES ('29', '个人信息', '/personal/index', '27', '否', '1', '2020-03-27', null, null);
INSERT INTO `tb_auth` VALUES ('30', 'aaaa', 'aaa', '1', '是', '1', '2020-03-29', null, null);
INSERT INTO `tb_auth` VALUES ('31', 'aa', '/exam/index1', '30', '否', '1', '2020-03-29', null, null);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `categId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `categName` varchar(20) DEFAULT NULL,
  `categContent` varchar(100) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`categId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', '教育', '教育栏目', '1', '2020-03-21', null, null);
INSERT INTO `tb_category` VALUES ('4', '军事', '军事栏目', '1', '2020-03-21', null, null);
INSERT INTO `tb_category` VALUES ('5', '经济', '经济栏目', '1', '2020-03-21', null, null);
INSERT INTO `tb_category` VALUES ('6', '文化', '文化栏目', '1', '2020-03-21', '1', '2020-03-21');
INSERT INTO `tb_category` VALUES ('7', '生态', '生态栏目', '1', '2020-03-21', null, null);
INSERT INTO `tb_category` VALUES ('8', '科技', '科技栏目', '1', '2020-03-21', null, null);

-- ----------------------------
-- Table structure for tb_errorques
-- ----------------------------
DROP TABLE IF EXISTS `tb_errorques`;
CREATE TABLE `tb_errorques` (
  `errorId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesId` int(8) DEFAULT NULL,
  `submitAnswer` varchar(10) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`errorId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_errorques
-- ----------------------------
INSERT INTO `tb_errorques` VALUES ('1', '18', 'X', '2', '2020-03-25 13:49:28', null, null);
INSERT INTO `tb_errorques` VALUES ('2', '15', 'A', '2', '2020-03-25 13:49:28', null, null);
INSERT INTO `tb_errorques` VALUES ('3', '24', 'X', '2', '2020-03-26 14:17:41', null, null);
INSERT INTO `tb_errorques` VALUES ('4', '27', 'X', '2', '2020-03-26 14:17:41', null, null);
INSERT INTO `tb_errorques` VALUES ('6', '1', 'A', '1', '2020-03-26 15:03:19', null, null);
INSERT INTO `tb_errorques` VALUES ('9', '1', 'X', '25', '2020-03-28 21:39:19', null, null);
INSERT INTO `tb_errorques` VALUES ('10', '19', 'B', '25', '2020-03-28 21:39:19', null, null);
INSERT INTO `tb_errorques` VALUES ('11', '21', 'C', '25', '2020-03-28 21:39:19', null, null);
INSERT INTO `tb_errorques` VALUES ('12', '18', 'A', '26', '2020-03-29 14:37:50', null, null);
INSERT INTO `tb_errorques` VALUES ('13', '19', 'B', '26', '2020-03-29 14:37:50', null, null);

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `newsId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `newsContent` varchar(200) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES ('1', '全国多个省确定2020上半年开学时间', '教育', '根据各省市教育厅官方网站消息，全国多个省确定2020上半年开学时间', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('2', '方法', '军事', '风动旛动', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('3', 'aa', '军事', 'aa', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('5', 'dfdf', '军事', 'fdsf', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('6', 'gg', '文化', 'gg', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('7', 'ggg', '教育', 'ggg', '1', '2020-03-21', null, null);
INSERT INTO `tb_news` VALUES ('9', 'qqaa', '6', 'qqqaa', '1', '2020-03-21', '1', '2020-03-21');

-- ----------------------------
-- Table structure for tb_quescollect
-- ----------------------------
DROP TABLE IF EXISTS `tb_quescollect`;
CREATE TABLE `tb_quescollect` (
  `collectId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesTypeName` varchar(30) DEFAULT NULL,
  `quesId` int(8) DEFAULT NULL,
  `quesContent` varchar(200) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`collectId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_quescollect
-- ----------------------------
INSERT INTO `tb_quescollect` VALUES ('5', 'spring', '18', '关于 Aspect.注解的介绍,说法错误的是()', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('7', 'spring', '22', '以下有关CGLB代理相关说法正确的是()。', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('16', 'spring', '10', '下列选项中,不属于Spring框架优点的是()。', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('17', 'mybatis', '24', '有关 My Batis工作原理说法错误的是()。', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('18', 'mybatis', '31', '关于 My Batis配置文件中< typeAliases>元素说法错误的是()', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('19', 'mybatis', '27', '以下有关 MyBatis映射文件中< insert>元素说法正确的是()', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('20', 'mybatis', '29', '以下有关<sq>元素说法错误的是()', '1', '2020-03-24', null, null);
INSERT INTO `tb_quescollect` VALUES ('23', 'mybatis', '28', '有关 My Batis配置文件中< settings>元素的说法错误的是()', '1', '2020-03-25', null, null);
INSERT INTO `tb_quescollect` VALUES ('24', 'spring', '18', '关于 Aspect.注解的介绍,说法错误的是()', '2', '2020-03-25', null, null);
INSERT INTO `tb_quescollect` VALUES ('25', 'spring', '1', '以下有关 Spring框架优点的说法正确的是()。', '1', '2020-03-26', null, null);
INSERT INTO `tb_quescollect` VALUES ('26', 'spring', '1', '以下有关 Spring框架优点的说法正确的是()。', '25', '2020-03-28', null, null);
INSERT INTO `tb_quescollect` VALUES ('27', 'spring', '19', '以下不属于 Proxy Factory Bean类中的常用可配置属性的是()。', '25', '2020-03-28', null, null);
INSERT INTO `tb_quescollect` VALUES ('28', 'spring', '14', 'Spring容器支持多种形式的Bean的装配方式,不包括有()。', '26', '2020-03-29', null, null);

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question` (
  `quesId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesType` int(8) DEFAULT NULL,
  `quesContent` varchar(200) DEFAULT NULL,
  `optionA` varchar(200) DEFAULT NULL,
  `optionB` varchar(200) DEFAULT NULL,
  `optionC` varchar(200) DEFAULT NULL,
  `optionD` varchar(200) DEFAULT NULL,
  `quesAnswer` varchar(50) DEFAULT NULL,
  `submitAnwer` varchar(50) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`quesId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES ('1', '4', '以下有关 Spring框架优点的说法正确的是()。', 'Spring!具有简单、可测试和松耦合等特点,从这个角度出发,spring就是应用于任何ava应用的开发中;', 'Spring提供了对AOP的支持,它允许将一些通用任务,如安全、事务、日志等进行集中式处理,从而提高了程  序的复用性', 'spring就是个大工厂,可以将所有对象的创建和依赖关系的维护工作都交给 Spring容器管理,杜绝了组件之  间的耦合性', 'spring增加了 Java ee开发中一些AP的使用难度', 'B', null, '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_question` VALUES ('2', '1', 'java基本数据类型', 'aa', 're', 'sdfd', 'fds', 'B', null, '1', '2020-03-22', null, null);
INSERT INTO `tb_question` VALUES ('4', '1', 'df', 'fd', 'sf', 'fd', 'fdfd', 'C', null, '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_question` VALUES ('10', '4', '下列选项中,不属于Spring框架优点的是()。', '提供强大的、可以有效减少页面代码的标签', '声明式事务的支持', '方便解耦、简化开发', '方便集成各种优秀框架', 'A', null, '1', '2020-03-22', null, null);
INSERT INTO `tb_question` VALUES ('11', '4', '下列有关 Spring框架的描述,错误的是()。', 'Spring是 Java Web开发框架', 'Spring是轻量级框架', 'Spring是开源框架', 'Spring是持久层框架', 'D', null, '1', '2020-03-22', '1', '2020-03-23');
INSERT INTO `tb_question` VALUES ('12', '4', 'Spring的核心容器是其他模块建立的基础,以下哪个不是该容器的组成', 'Beans模块', 'core模块', 'Context模块', 'AOP模块', 'D', null, '1', '2020-03-22', null, null);
INSERT INTO `tb_question` VALUES ('13', '4', 'Spring的<bean>元素中的 autowire属性取值不包括以下()。', 'default', 'by Name', 'by Type', 'byld', 'D', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('14', '4', 'Spring容器支持多种形式的Bean的装配方式,不包括有()。', '基于XML的装配', '基于 properties的装配', '基于注解( Annotation)的装配', '自动装配', 'B', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('15', '4', '下列选项中,不属于spring中实例化Bean的方式的是()。', '构造器实例化', '静态工厂方式实例化', '实例工厂方式实例化', '抽象方法实例化', 'D', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('16', '4', '以下哪些不属于 Spring43版本中Bean的作用域()', 'application', 'request', 'response', 'globalSession', 'C', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('17', '4', '以下哪些不属于实例化Bean的方式()。', '构造器实例化', '静态工厂方式实例化', '实例工厂方式实例化', '抽象方法实例化', 'D', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('18', '4', '关于 Aspect.注解的介绍,说法错误的是()', '@AspectE于定义一个切面', '@Pointcut于定义切入点表达式', '@Before用于定义前置通知,相当于 BeforeAdvice', '@Afe用于定义后置通知,相当于 AfterReturningAdvice', 'D', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('19', '4', '以下不属于 Proxy Factory Bean类中的常用可配置属性的是()。', 'target', 'proxylnterfaces', 'targetclass', 'interceptorNames', 'C', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('21', '4', '下列有关AOP专业术语中,用于指那些被拦截到的点的是()。', 'Joinpoint', 'Pointcut', 'Aspect', 'Advice', 'A', null, '1', '2020-03-23', '1', '2020-03-23');
INSERT INTO `tb_question` VALUES ('22', '4', '以下有关CGLB代理相关说法正确的是()。', 'CGLB代理的使用非常简单,但它还有一定的局限性—使用动态代理的对象必须实现一个或多个接口', '如果要对没有实现接口的类进行代理,那么可以使用JDK代理', 'CGLB是一个高性能开源的代码生成包,在使用时需要另外导入CGLB所需要的包', 'spring中的AOP代理,可以是JDK动态代理,也可以是CGLB代理', 'D', null, '1', '2020-03-23', null, null);
INSERT INTO `tb_question` VALUES ('23', '6', 'MyBatis的删除操作有关说发错误的是()。', 'MyBatis的删除操作在映射文件中是通过配置delete元素来实现的', 'MyBatis的删除操作也需要进行事务提交', 'MyBatish的删除操作执行了 Sqlsession的oete(方法', 'My Batis的删除操作和添加操作时,也需要封装整个实体类', 'D', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('24', '6', '有关 My Batis工作原理说法错误的是()。', 'MyBatis的全局配置文件配置了 MyBatis的运行环境等信息,其中主要内容是获取数据库连接', 'MyBatis映射文件中配置了操作数据库的SQL语句,需要在 My Batish的全局配置文件中加载才能执行', '可以通过 My Batis的环境等配置信息构建会话对象 Sqlsession', 'Sqlsessior对象,该对象中包含了执行SQL的所有方法', 'C', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('25', '6', 'MyBatis的更新操作有关说发错误的是()', 'MyBatis的更新操作在映射文件中是通过配置 update元素来实现的', 'MyBatis的更新操作也需要进行事务提交', 'MyBatis的更新操作执行了 Sqlsession的 update方法', 'MyBatis的更新操作和添加操作时,只需要将 insert()法改为 updat(方法即可', 'D', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('26', '6', '使用ORM框架后,应用程序不再直接访问底层数据库,而是以()的  方式来操作持久化对象(PO,即 Persisent object)。', '面向业务', 'Hibernate', '面向对象', 'MyBatis', 'C', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('27', '6', '以下有关 MyBatis映射文件中insert元素说法正确的是()', 'insert元素用于映射插入语句,在执行完元素中定义的SQL语句后,没有返回结果', ' insert元素的属性与select元素的属性相同', ' key Column属性用于设置第几列是主键,当主键列不是表中的第一列时需要设置', ' use GeneratedKeys(仅对 insert有用)此属性会使 My Batis使用JDBc的 getGenerated Keys0  方法来获取由数据库内部生产的主键', 'C', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('28', '6', '有关 My Batis配置文件中settings元素的说法错误的是()', 'settings元素主要用于改变 My Batis运行时的行为,例如开启二级缓存、开启延迟加载等', '虽然不配置< settings>元素,也可以正常运行 My Batis,但是熟悉 settings的配置内容以及它们  的作用还是十分必要的', ' settings元素中延迟加载的全局开关的参数 lazy Enabled默认开启', ' settings元素的配置内容大多数都不需要开发人员去配置它,通常在需要时只配置少数几项即可', 'C', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('29', '6', '以下有关sql元素说法错误的是()', 'sql元素的作用就是定义可重用的soL代码片段,然后在其他语句中引用这一代码片段', '使用include元素的refid属性可以引用自定义的代码片段', '使用include元素refd的属性值为自定义代码片段的name', 'sql元素是mapper元素的子元素', 'C', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('30', '6', '关于typeHandlers元素说法错误的是', 'typeHandler元素就是用来在配置文件中注册自定义的类型处理器的,它的使用方式有两种', '通过typeHandlers元素的子元素typeHandle就可以配置单个类型处理器', '通过 typeHandlers元素的子元素 package可以配置包下的所有类型处理器', 'typeHandlers元素的子元素 package的 handler属性用于指定类型处理器所在的包名,系统会  在启动时自动的扫描 com. itheimatype包下所有的文件,并把它们作为类型处理器', 'D', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('31', '6', '关于 My Batis配置文件中typeAliases元素说法错误的是()', '可以通过在 MyBaits中配置typeAliases元素来定义别名', 'My Baits配置文件中typeAliases元素的子元素type Alias需要配置type和aias属性', '可以通过在类名上使用@Aias(vaue=\"user)的形式定义别名', '同时在 MyBaits中配置了 typeAliases元素以及@ Alias( value=\"user\")注解定义别名后,会以注  解形式的别名为准', 'B', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('33', '6', 'sQL语句中, where后直接跟and、这在运行时肯定会报()错误', 'sQL语法', '无效字符', '参数异常', 'MySQL版本', 'A', null, '1', '2020-03-24', null, null);
INSERT INTO `tb_question` VALUES ('34', '6', '以下不属于foreach元素中使用的属性的是()。', 'separator', 'collection', 'current', 'item', 'C', null, '1', '2020-03-24', null, null);

-- ----------------------------
-- Table structure for tb_question_old
-- ----------------------------
DROP TABLE IF EXISTS `tb_question_old`;
CREATE TABLE `tb_question_old` (
  `quesId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesContent` varchar(200) DEFAULT NULL,
  `quesAnswer` varchar(100) DEFAULT NULL,
  `quesType` int(8) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`quesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_question_old
-- ----------------------------

-- ----------------------------
-- Table structure for tb_questype
-- ----------------------------
DROP TABLE IF EXISTS `tb_questype`;
CREATE TABLE `tb_questype` (
  `quesTypeId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesTypeName` varchar(20) DEFAULT NULL,
  `quesTypeContent` varchar(20) DEFAULT NULL,
  `examTime` int(10) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`quesTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_questype
-- ----------------------------
INSERT INTO `tb_questype` VALUES ('1', 'Java知识1', 'Java知识练习', '1', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('3', 'Java知识2', 'Java知识练习', '1', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('4', 'spring', 'spring知识练习', '1', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('5', 'springmvc', 'springmvc知识练习', '1', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('6', 'mybatis', 'mybatis知识练习', '2', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('7', 'javascript', 'javascript知识练习', '1', '1', '2020-03-22', '1', '2020-03-22');
INSERT INTO `tb_questype` VALUES ('17', '设计模式', '掌握常用的设计模式', '5', '1', '2020-03-28', '1', '2020-03-28');
INSERT INTO `tb_questype` VALUES ('18', 'mysql', 'mysql练习', '10', '1', '2020-03-28', null, null);
INSERT INTO `tb_questype` VALUES ('19', 'oracle', 'oracle知识练习', '3', '1', '2020-03-29', null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `roleId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `roleContent` varchar(100) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '系统管理员', '管理、维护系统', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('2', '项目经理', '设计、实施、维护项目', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('3', '前端工程师', '负责页面的布局', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('5', '后端工程师', '数据逻辑存储（数据库、redis等），复杂逻辑的设计', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('6', '测试工程师', '测试程序', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('7', '运维工程师', '系统运维', '1', '2020-03-21', '1', '2020-03-26');
INSERT INTO `tb_role` VALUES ('12', '美工', '界面设计', '1', '2020-03-27', null, null);
INSERT INTO `tb_role` VALUES ('13', 'rrr', 'rrr', '1', '2020-03-29', null, null);

-- ----------------------------
-- Table structure for tb_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_auth`;
CREATE TABLE `tb_role_auth` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(8) DEFAULT NULL,
  `authId` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_auth
-- ----------------------------
INSERT INTO `tb_role_auth` VALUES ('1', '1', '1');
INSERT INTO `tb_role_auth` VALUES ('2', '1', '2');
INSERT INTO `tb_role_auth` VALUES ('3', '1', '3');
INSERT INTO `tb_role_auth` VALUES ('4', '1', '4');
INSERT INTO `tb_role_auth` VALUES ('5', '1', '5');
INSERT INTO `tb_role_auth` VALUES ('6', '1', '6');
INSERT INTO `tb_role_auth` VALUES ('7', '1', '7');
INSERT INTO `tb_role_auth` VALUES ('8', '1', '8');
INSERT INTO `tb_role_auth` VALUES ('9', '1', '9');
INSERT INTO `tb_role_auth` VALUES ('10', '2', '1');
INSERT INTO `tb_role_auth` VALUES ('11', '2', '7');
INSERT INTO `tb_role_auth` VALUES ('12', '2', '8');
INSERT INTO `tb_role_auth` VALUES ('13', '2', '9');
INSERT INTO `tb_role_auth` VALUES ('14', '1', '11');
INSERT INTO `tb_role_auth` VALUES ('16', '2', '11');
INSERT INTO `tb_role_auth` VALUES ('23', '1', '20');
INSERT INTO `tb_role_auth` VALUES ('24', '1', '21');
INSERT INTO `tb_role_auth` VALUES ('25', '2', '21');
INSERT INTO `tb_role_auth` VALUES ('27', '1', '23');
INSERT INTO `tb_role_auth` VALUES ('28', '1', '24');
INSERT INTO `tb_role_auth` VALUES ('29', '2', '23');
INSERT INTO `tb_role_auth` VALUES ('30', '2', '24');
INSERT INTO `tb_role_auth` VALUES ('31', '1', '25');
INSERT INTO `tb_role_auth` VALUES ('32', '2', '25');
INSERT INTO `tb_role_auth` VALUES ('36', '3', '1');
INSERT INTO `tb_role_auth` VALUES ('37', '3', '7');
INSERT INTO `tb_role_auth` VALUES ('38', '3', '8');
INSERT INTO `tb_role_auth` VALUES ('39', '3', '9');
INSERT INTO `tb_role_auth` VALUES ('40', '5', '8');
INSERT INTO `tb_role_auth` VALUES ('42', '5', '1');
INSERT INTO `tb_role_auth` VALUES ('43', '5', '7');
INSERT INTO `tb_role_auth` VALUES ('50', '1', '27');
INSERT INTO `tb_role_auth` VALUES ('52', '3', '27');
INSERT INTO `tb_role_auth` VALUES ('53', '5', '27');
INSERT INTO `tb_role_auth` VALUES ('54', '6', '27');
INSERT INTO `tb_role_auth` VALUES ('55', '7', '27');
INSERT INTO `tb_role_auth` VALUES ('56', '12', '1');
INSERT INTO `tb_role_auth` VALUES ('57', '12', '2');
INSERT INTO `tb_role_auth` VALUES ('58', '12', '3');
INSERT INTO `tb_role_auth` VALUES ('59', '12', '7');
INSERT INTO `tb_role_auth` VALUES ('60', '12', '11');
INSERT INTO `tb_role_auth` VALUES ('61', '12', '27');
INSERT INTO `tb_role_auth` VALUES ('62', '1', '29');
INSERT INTO `tb_role_auth` VALUES ('63', '2', '27');
INSERT INTO `tb_role_auth` VALUES ('64', '1', '30');
INSERT INTO `tb_role_auth` VALUES ('65', '2', '30');
INSERT INTO `tb_role_auth` VALUES ('66', '3', '30');
INSERT INTO `tb_role_auth` VALUES ('67', '5', '30');
INSERT INTO `tb_role_auth` VALUES ('68', '6', '30');
INSERT INTO `tb_role_auth` VALUES ('69', '7', '30');
INSERT INTO `tb_role_auth` VALUES ('70', '12', '30');
INSERT INTO `tb_role_auth` VALUES ('71', '1', '30');
INSERT INTO `tb_role_auth` VALUES ('72', '1', '31');
INSERT INTO `tb_role_auth` VALUES ('73', '2', '31');
INSERT INTO `tb_role_auth` VALUES ('74', '13', '1');
INSERT INTO `tb_role_auth` VALUES ('75', '13', '2');
INSERT INTO `tb_role_auth` VALUES ('76', '13', '3');
INSERT INTO `tb_role_auth` VALUES ('77', '13', '7');
INSERT INTO `tb_role_auth` VALUES ('78', '13', '11');
INSERT INTO `tb_role_auth` VALUES ('79', '13', '27');
INSERT INTO `tb_role_auth` VALUES ('80', '13', '30');

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
  `testId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `quesTypeName` varchar(30) DEFAULT NULL,
  `grade` int(8) DEFAULT NULL,
  `quesIds` varchar(100) DEFAULT NULL,
  `submitAnswer` varchar(30) DEFAULT NULL,
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`testId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES ('31', 'mybatis', '50', '31, 29, 30, 23, 25, 34, 33, 24, 28, 26', 'DXCDDCABXC', '1', '2020-03-24', null, null);
INSERT INTO `tb_test` VALUES ('32', 'spring', '100', '16, 14, 17, 19, 22, 13, 18, 10, 12, 1', 'CBDCDDDADB', '1', '2020-03-24', null, null);
INSERT INTO `tb_test` VALUES ('33', 'mybatis', '50', '33, 30, 27, 29, 23, 34, 24, 31, 28, 25', 'ADDCDCDDXX', '1', '2020-03-25', null, null);
INSERT INTO `tb_test` VALUES ('34', 'mybatis', '40', '31, 27, 28, 23, 33, 34, 29, 30, 24, 25', 'DDDDACCXXX', '1', '2020-03-25', null, null);
INSERT INTO `tb_test` VALUES ('35', 'spring', '80', '21, 1, 22, 13, 12, 18, 14, 17, 11, 15', 'ABDDDXBDDA', '2', '2020-03-25', null, null);
INSERT INTO `tb_test` VALUES ('36', 'mybatis', '70', '31, 23, 25, 34, 33, 30, 29, 27, 28, 24', 'BDDCADCXXX', '2', '2020-03-26', null, null);
INSERT INTO `tb_test` VALUES ('37', 'spring', '90', '21, 14, 10, 18, 22, 15, 19, 1, 17, 11', 'ABADDDCADD', '1', '2020-03-26', null, null);
INSERT INTO `tb_test` VALUES ('38', 'spring', '90', '21, 14, 10, 18, 22, 15, 19, 1, 17, 11', 'ABADDDCADD', '1', '2020-03-26', null, null);
INSERT INTO `tb_test` VALUES ('39', 'spring', '90', '21, 14, 10, 18, 22, 15, 19, 1, 17, 11', 'ABADDDCADD', '1', '2020-03-26', null, null);
INSERT INTO `tb_test` VALUES ('40', 'spring', '70', '13, 16, 22, 15, 14, 19, 10, 17, 21, 1', 'DCDDBBADCX', '25', '2020-03-28', null, null);
INSERT INTO `tb_test` VALUES ('41', 'spring', '70', '11, 1, 12, 16, 21, 14, 17, 22, 18, 19', 'DBDCACDDAB', '26', '2020-03-29', null, null);

-- ----------------------------
-- Table structure for tb_test_ques
-- ----------------------------
DROP TABLE IF EXISTS `tb_test_ques`;
CREATE TABLE `tb_test_ques` (
  `testId` int(8) NOT NULL,
  `quesId` int(8) NOT NULL,
  PRIMARY KEY (`testId`,`quesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_test_ques
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userTrueName` varchar(20) DEFAULT NULL,
  `userState` varchar(2) DEFAULT '0',
  `creator` int(8) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updater` int(8) DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', 'hello', '1', '1', '2020-03-20', null, null);
INSERT INTO `tb_user` VALUES ('2', 'hello1', 'hello1', 'hello1', '1', '1', '2020-03-20', null, null);
INSERT INTO `tb_user` VALUES ('3', 'hello2', 'hello2', 'hello2', '0', '1', '2020-03-20', null, null);
INSERT INTO `tb_user` VALUES ('15', 'aaa', 'aaa', 'aaa', '1', '1', '2020-03-20', '1', '2020-03-20');
INSERT INTO `tb_user` VALUES ('16', 'bbb', 'bbb', 'bbb', '1', '1', '2020-03-20', '1', '2020-03-26');
INSERT INTO `tb_user` VALUES ('17', 'ccc', 'ccc', 'ccc', '1', '1', '2020-03-20', '1', '2020-03-20');
INSERT INTO `tb_user` VALUES ('19', 'dddd', 'dddd', 'dddd', '1', '1', '2020-03-21', '1', '2020-03-21');
INSERT INTO `tb_user` VALUES ('21', '111', '111', '111', '0', '1', '2020-03-27', null, null);
INSERT INTO `tb_user` VALUES ('22', '222', '222', '222', '0', '1', '2020-03-27', null, null);
INSERT INTO `tb_user` VALUES ('23', '333', '333', '333', '0', '1', '2020-03-27', null, null);
INSERT INTO `tb_user` VALUES ('24', '555', '555', '555', '0', '1', '2020-03-27', null, null);
INSERT INTO `tb_user` VALUES ('25', '666', '666', '666', '1', '1', '2020-03-28', '1', '2020-03-28');
INSERT INTO `tb_user` VALUES ('26', '777', '7777', '777', '1', '1', '2020-03-29', '1', '2020-03-29');

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth` (
  `userAuthId` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(8) DEFAULT NULL,
  `authId` int(8) DEFAULT NULL,
  PRIMARY KEY (`userAuthId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `userId` int(8) NOT NULL,
  `roleId` int(8) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '2');
INSERT INTO `tb_user_role` VALUES ('15', '5');
INSERT INTO `tb_user_role` VALUES ('17', '3');
INSERT INTO `tb_user_role` VALUES ('25', '2');
INSERT INTO `tb_user_role` VALUES ('26', '2');
