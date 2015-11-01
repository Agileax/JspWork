var menuData = [
	{
		"cols":[["id","id"],["name","姓名"],["phone","电话"],["address","地址"],["email","邮箱"]],
		"model":"com.app.bean.Client",
		"menu":"客户管理"
	},
	{
		"cols":[["id","id"],["name","客户姓名"],["contents","合同内容"],["start","合同生效日期"],["end","合同有效期"],["staff","业务员"]],
		"model":"com.app.bean.Contact",
		"menu":"合同管理"
	},
	{
		"cols":[["id","id"],["clientName","客户姓名"],["clientOpinion","客户反馈意见"],["staff","业务员"]],
		"model":"com.app.bean.CS",
		"menu":"售后管理"
	},
	{
		"cols":[["id","id"],["name","产品名称"],["modelNum","产品型号"],["number","产品数量"],["price","产品价格"]],
		"model":"com.app.bean.Product",
		"menu":"产品管理"
	},
	{
		"cols":[["id","id"],["name","姓名"],["sex","性别"],["age","年龄"],["education","学历"],["department","部门"],["date","入职时间"],["duty","职务"],["wage","工资"]],
		"model":"com.app.bean.Staff",
		"menu":"员工管理"
	}
];
		