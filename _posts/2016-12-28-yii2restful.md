---
layout: default

title: yii2 restful

---

##yii2 restful

###First, create a controller class 

app\controllers\UserController as follows:
	
	namespace app\controllers;
	
	use yii\rest\ActiveController;
	
	class UserController extends ActiveController
	{
	    public $modelClass = 'app\models\User';
	}
	
###Configuring URL Rules

Then, modify the configuration of the urlManager component in your application configuration:

	'urlManager' => [
	    'enablePrettyUrl' => true,
	    'enableStrictParsing' => true,
	    'showScriptName' => false,
	    'rules' => [
	        ['class' => 'yii\rest\UrlRule', 'controller' => 'user'],
	    ],
	]
	
	
	GET /users: list all users page by page;
	HEAD /users: show the overview information of user listing;
	POST /users: create a new user;
	GET /users/123: return the details of the user 123;
	HEAD /users/123: show the overview information of user 123;
	PATCH /users/123 and PUT /users/123: update the user 123;
	DELETE /users/123: delete the user 123;
	OPTIONS /users: show the supported verbs regarding endpoint /users;
	OPTIONS /users/123: show the supported verbs regarding endpoint /users/123.

###override action

	public function actions()  
	{  
	    $actions = parent::actions();  
	  
	    // 注销系统自带的实现方法  
	    unset($actions['index']);  
	      
	    //unset($actions['create']);  
	    //unset($actions['update']);  
	    //unset($actions['delete']);  
	  
	  return $actions;  
	}  
	  
	//覆盖父类的actionIndex方法,并进行重写  
	public function actionIndex()  
	{  
	    //获取用户所有信息  
	    ......  
	}  
或者修改urlmanger


	'urlManager' => [
	            'enablePrettyUrl' => true,
	          //  'enableStrictParsing' => true,
	            'showScriptName' => false,
	            'rules' => [
	                [
	
	                    'class' => 'yii\rest\UrlRule', 
	                    'controller' => ['user'],
	                    'extraPatterns' => [
	                        'POST /adduser' => 'adduser', // 'adduser' refers to 'actionAdduser'
	                        'POST /getuser' => 'getuser',
	                        
	                    ],
	
	
	                ],
	            ],
	            
	        ],
        