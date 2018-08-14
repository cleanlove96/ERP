/**
 * organization-list.jsp
 */
// 树形结构的设置
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "roleId",
			pIdKey : "sectionId"
		}
	},
	// 拖动相关的设置
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		drag : {
			isCopy : false,
			isMove : true
		}
	},
	async : {
		enable : true,
		url : "sectionRoleController/initZtree.do",
		autoParam : [],
		type: "POST"
		//otherParam: ["id", "1"]
	},
	// 回调：拖动触发的剧情
	callback : {
		beforeDrop : bDrop,
		onClick : oClick
	},
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "p", "N": "s" }
	}
};
// 树形结构的节点信息
/*var nodes = [ {
	'id' : '1',
	'pId' : '0',
	'name' : '公司',
	'open' : 'true',
	'icon' : 'images/company.png',
	'type' : 'company'
}, {
	'id' : '2',
	'pId' : '1',
	'name' : '部门1',
	'icon' : 'images/department.png',
	'type' : 'department'
}, {
	'id' : '3',
	'pId' : '1',
	'name' : '部门2',
	'icon' : 'images/department.png',
	'type' : 'department'
}, {
	'id' : '4',
	'pId' : '1',
	'name' : '部门3',
	'icon' : 'images/department.png',
	'type' : 'department'
}, {
	'id' : '5',
	'pId' : '2',
	'name' : '子部门1',
	'icon' : 'images/department.png',
	'type' : 'department'
}, {
	'id' : '6',
	'pId' : '3',
	'name' : '员工1',
	'icon' : 'images/staff.png',
	'type' : 'staff'
} ];*/

// 使用ajax生成树
$(document).ready(function() {
	$.fn.zTree.init($("#companytree"), setting);
});

/* 拖动操作，更新数据库的节点结构 */
function bDrop(reeId, treeNodes, targetNode, moveType) {
	var n = treeNodes[0];
	if (n.type == 'staff') {

		return true;
	} else {
		alert("仅允许调动员工位置！");
		return false;
	}
};

/* 点击操作，如果是员工，显示该节点信息，否则展开/折叠该节点 */
function oClick(event, treeId, treeNode) {
	// 获取树对象
	var companytree = $.fn.zTree.getZTreeObj("companytree");
	if (treeNode.style == 'staff') {
		// 如果点击的是员工节点，进行之后的操作

	} else {
		// 否则，展开/关闭该节点
		companytree.expandNode(treeNode);
	}
};
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
	$.ajax({
		url : "indexController/drop.do",
		type : "POST",
		data : {
			pid : targetNode.id,
			id : treeNodes[0].id
		},
		dataType : "text",
		success : function(res) {
			if (res == "SUCCESS") {

			} else {
				window.location.reload();
			}
		},
		error : function() {

		}
	});
};
