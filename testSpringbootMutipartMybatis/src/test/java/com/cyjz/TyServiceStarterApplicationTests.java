package com.cyjz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyjz.dao.mapper.MenuMapper;
import com.cyjz.pojo.Menu;
import com.cyjz.util.CommUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TyServiceStarterApplicationTests {


	@Autowired
	private MenuMapper menuMapper; 
	
//	@Autowired
//	private MenuMapper2 menuMapper2;
	
	/**
	 * 数据库数据转换为json格式
	 */
	@Test
	public void contextLoads() {
		List<Menu> list = menuMapper.selectMenuByRoleId(1L);
		List<Map<String,Object>> result = new ArrayList<>();
		for (Menu menu : list) {
			if(menu.getLevel() == 1) {
				Map<String,Object> map = new HashMap<>();
				map.put("name", menu.getName());
				map.put("title", menu.getTitle());
				map.put("icon", menu.getIcon());
				map.put("jump", menu.getJump());
				List<Menu> level2 = getChildrens(list,menu.getId());
				List<Map<String,Object>> level2List = new ArrayList<>();
				for (Menu menu2 : level2) {
					Map<String,Object> map2 = new HashMap<>();
					map2.put("name", menu2.getName());
					map2.put("title", menu2.getTitle());
					map2.put("jump", menu2.getJump());
					List<Menu> level3 = getChildrens(list, menu2.getId());
					List<Map<String,Object>> level3List =  new ArrayList<>();
					if(level3.size() > 0) {
						for (Menu menu3 : level3) {
							Map<String,Object> map3 = new HashMap<>();
							map3.put("name", menu3.getName());
							map3.put("title", menu3.getTitle());
							map3.put("jump", menu3.getJump());
							level3List.add(map3);
						}
					}
					map2.put("list", level3List);
					level2List.add(map2);
				}
				map.put("list", level2List);
				result.add(map);
			}
		}
		JSONArray json = CommUtil.toJSON(result, null);
		System.err.println(json);
	}
	/**
	 * 获得子节点
	 * @param list
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildrens(List<Menu> list, Long parentId){
		List<Menu> menuList = new ArrayList<>();
		for (Menu menu : list) {
//			if(CommUtil.null2Long(menu.getParentId()) == parentId) {
//				menuList.add(menu);
//			}
		}
		return menuList;
	}
	
	public boolean addMenu(Menu menu) {
		int insert = menuMapper.insert(menu);
		return insert > 0  ? true : false ;
	}
	
	
	//实现方法
	@Test
	public void jsonToList() {

		/**
		 * 数据
		 */
		String str="{\r\n" + 
				"	\"code\": 0,\r\n" + 
				"	\"msg\": \"\",\r\n" + 
				"	\"data\": [{\r\n" + 
				"		\"name\": \"personal_manage\",\r\n" + 
				"		\"title\": \"人事行政模块*\",\r\n" + 
				"		\"icon\": \"layui-icon-home\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"			\"name\": \"organizationStructure\",\r\n" + 
				"			\"title\": \"组织架构管理*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"					\"name\": \"organizationStructureList\",\r\n" + 
				"					\"title\": \"组织机构编辑*\"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"personnelScheduling\",\r\n" + 
				"					\"title\": \"人员调动\",\r\n" + 
				"					\"jump\": \"/personal_manage/personnelScheduling/index\"\r\n" + 
				"				}\r\n" + 
				"			]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"huamingce\",\r\n" + 
				"			\"title\": \"花名册*\",\r\n" + 
				"			\"icon\": \"layui-icon-component\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"					\"name\": \"huamingce_manager\",\r\n" + 
				"					\"title\": \"员工信息管理*\"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"employee_contract_management\",\r\n" + 
				"					\"title\": \"员工合同管理\",\r\n" + 
				"					\"jump\": \"/personal_manage/huamingce/employeeContractManagement\"\r\n" + 
				"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"employee_address_list_management\",\r\n" + 
				"					\"title\": \"员工通讯录管理*\",\r\n" + 
				"					\"jump\": \"/personal_manage/huamingce/contactListManagement\"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"blackListManagement\",\r\n" + 
				"					\"title\": \"黑名单管理*\"\r\n" + 
				"				}\r\n" + 
				"			]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"train_management\",\r\n" + 
				"			\"title\": \"培训管理\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"train_course_management\",\r\n" + 
				"				\"title\": \"培训计划管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/trainingManagement/trainingManagementList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"personalInfo\",\r\n" + 
				"			\"title\": \"人员信息管理*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"					\"name\": \"personInfoList\",\r\n" + 
				"					\"title\": \"人员信息列表*\"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"employeeDocumentsList\",\r\n" + 
				"					\"title\": \"人员证件列表*\",\r\n" + 
				"					\"jump\": \"/personal_manage/personalInfo/employeeDocuments/employeeDocumentsList\"\r\n" + 
				"				}\r\n" + 
				"\r\n" + 
				"			]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"recruit_management\",\r\n" + 
				"			\"title\": \"招聘管理\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"recruit_plan_management\",\r\n" + 
				"				\"title\": \"招聘计划管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/recruitmentManager/recruitmentPlanManagement/index\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"recruit_record\",\r\n" + 
				"				\"title\": \"招聘记录\",\r\n" + 
				"				\"jump\": \"/personal_manage/hiring/record/index\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"talent_pool\",\r\n" + 
				"				\"title\": \"备用人才库\",\r\n" + 
				"				\"jump\": \"/personal_manage/recruitmentManager/reserveRecruitPool\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"person_application\",\r\n" + 
				"				\"title\": \"用人申请\",\r\n" + 
				"				\"jump\": \"/personal_manage/recruitmentManager/recruitmentApplication\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"person_application_record\",\r\n" + 
				"				\"title\": \"用人申请记录\",\r\n" + 
				"				\"jump\": \"/personal_manage/recruitmentManager/recruitmentApplicationList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"住房福利\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"福利房管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/welfareHousingManagement/welfareHousingManagementList/welfareHousingManagementList\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"福利房利入住管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/welfareHousingManagement/welfareHousingMccupancyManagement/welfareHousingMccupancyManagementList\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"福利房利巡检管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/welfareHousingManagement/welfareHousingViolation/welfareHousingViolationList\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"福利房利申请管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/welfareHousingManagement/applicationRecordForWelfareHousing/applicationRecordForWelfareHousingList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"工时福利\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"工时福利设置\",\r\n" + 
				"				\"jump\": \"/personal_manage/workingHoursWelfare/workingHoursWefareSet\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"工时福利管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/workingHoursWelfare/workingHoursWefareManage\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"annualVacationManager\",\r\n" + 
				"			\"title\": \"年假福利*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"annualVacationSetting/annualVacationSetting\",\r\n" + 
				"				\"title\": \"年假设置*\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"annualVacationTab/annualVacationTab\",\r\n" + 
				"				\"title\": \"员工年假列表\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"年假申请\",\r\n" + 
				"				\"jump\": \"/personal_manage/annualVacationManager/annualVacationApplication\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"员工晋升\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"晋升申请管理\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"晋升记录管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeePromotion/promotionRecordManageList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"员工奖惩\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"处罚记录管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeePrizeInfo/punishApplicationList\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"处罚申请管理\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			},  {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"奖励记录管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeePrizeInfo/rewordsApplicationList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"员工入职\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"员工入职审核规则\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeeEnter/employeeEnterCheckRules\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"员工入职审核列表\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeeEnter/employeeEnterCheckList\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"员工离职\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"员工离职申请管理\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeeTurnover/employeeTurnoverList\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"员工离职记录\",\r\n" + 
				"				\"jump\": \"/personal_manage/employeeTurnover/employeeTurnoverRecord\"\r\n" + 
				"			}]\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"name\": \"carManager\",\r\n" + 
				"		\"title\": \"车辆管理模块*\",\r\n" + 
				"		\"icon\": \"layui-icon-component\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"				\"name\": \"car\",\r\n" + 
				"				\"title\": \"车辆管理*\"\r\n" + 
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"name\":\"areaInfo\",\r\n" + 
				"				\"title\":\"区域信息维护\"\r\n" + 
				"				\r\n" + 
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"name\": \"layer\",\r\n" + 
				"				\"title\": \"违章信息\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"list\",\r\n" + 
				"					\"title\": \"违章信息列表\",\r\n" + 
				"					\"jump\": \"/carManager/violation/violationList/index\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"special-demo\",\r\n" + 
				"					\"title\": \"违章反馈记录\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}]\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"layer\",\r\n" + 
				"				\"title\": \"事故信息*\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"list\",\r\n" + 
				"					\"title\": \"事故信息列表*\",\r\n" + 
				"					\"jump\": \"/carManager/accidentInfo/accidentList\"\r\n" + 
				"				}]\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"tyreManage\",\r\n" + 
				"				\"title\": \"轮胎管理*\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"maintain\",\r\n" + 
				"				\"title\": \"维修计划*\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"maintainPlan\",\r\n" + 
				"					\"title\": \"维修计划列表*\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"special-demo\",\r\n" + 
				"					\"title\": \"维修申请\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"theme\",\r\n" + 
				"					\"title\": \"维修反馈\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"theme\",\r\n" + 
				"					\"title\": \"维修申请记录\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}]\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"layer\",\r\n" + 
				"				\"title\": \"保养计划*\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"list\",\r\n" + 
				"					\"title\": \"保养计划列表*\",\r\n" + 
				"					\"jump\": \"/carManager/carUpkeep/carUpkeepList\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"special-demo\",\r\n" + 
				"					\"title\": \"保养申请\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"theme\",\r\n" + 
				"					\"title\": \"保养反馈\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"theme\",\r\n" + 
				"					\"title\": \"保养等级设置\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"theme\",\r\n" + 
				"					\"title\": \"保养申请记录\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}]\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"parkingPlan\",\r\n" + 
				"				\"title\": \"停车计划*\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"parkingPlanList\",\r\n" + 
				"					\"title\": \"停车管理*\"\r\n" + 
				"				}]\r\n" + 
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"name\": \"layer\",\r\n" + 
				"				\"title\": \"车辆物品库存*\",\r\n" + 
				"				\"list\": [{\r\n" + 
				"					\"name\": \"special-demo*\",\r\n" + 
				"					\"title\": \"维修保养物品出入库管理\",\r\n" + 
				"					\"jump\": \"/carManager/carMaterialsManage/maintenanceMaterialsInList\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"special-demo\",\r\n" + 
				"					\"title\": \"随车物品入库登记\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}, {\r\n" + 
				"					\"name\": \"special-demo\",\r\n" + 
				"					\"title\": \"随车物品出库登记\",\r\n" + 
				"					\"jump\": \"/template/tips/404\"\r\n" + 
				"				}]\r\n" + 
				"			}\r\n" + 
				"		]\r\n" + 
				"	}, {\r\n" + 
				"		\"name\": \"trafficOperation\",\r\n" + 
				"		\"title\": \"运作管理*\",\r\n" + 
				"		\"icon\": \"layui-icon-component\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"			\"name\": \"transportMonitor\",\r\n" + 
				"			\"title\": \"实时监控*\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"roadBook\",\r\n" + 
				"			\"title\": \"路书管理*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"roadBookList\",\r\n" + 
				"				\"title\": \"路书制定*\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"roadGradeList\",\r\n" + 
				"				\"title\": \"路段等级管理*\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"dangerousRoadList\",\r\n" + 
				"				\"title\": \"危险路段管理*\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"carResources\",\r\n" + 
				"			\"title\": \"车辆资源*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"availableCarList\",\r\n" + 
				"				\"title\": \"可用车辆列表*\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"humanResources\",\r\n" + 
				"			\"title\": \"人员资源*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"availableHumanResourcesList\",\r\n" + 
				"				\"title\": \"可用人员列表*\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"dailyPlan\",\r\n" + 
				"			\"title\": \"每日计划*\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"					\"name\": \"transportation_management\",\r\n" + 
				"					\"title\": \"运输计划管理*\"\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"name\": \"customerManagement\",\r\n" + 
				"					\"title\": \"客户需求管理*\"\r\n" + 
				"				}\r\n" + 
				"			]\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"name\": \"system_flow\",\r\n" + 
				"		\"title\": \"个人中心\",\r\n" + 
				"		\"icon\": \"layui-icon-component\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"个人信息\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"我的简历\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"special-demo\",\r\n" + 
				"				\"title\": \"我的证件\",\r\n" + 
				"				\"jump\": \"/systemFlow/myCertificates/myCertificates\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"我的信息\",\r\n" + 
				"				\"jump\": \"/systemFlow/myInformation/index\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"待办任务\",\r\n" + 
				"				\"jump\": \"/systemFlow/myTask/myTask\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"已办任务\",\r\n" + 
				"				\"jump\": \"/systemFlow/historicalTask/historicalTask\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"theme\",\r\n" + 
				"				\"title\": \"我的申请\",\r\n" + 
				"				\"jump\": \"/systemFlow/myTask/myTask\"\r\n" + 
				"			}\r\n" + 
				"			]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"我的工时福利\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"工时福利申请\",\r\n" + 
				"				\"jump\": \"/systemFlow/myWorkBenefits/workBenefits/index\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"请假管理\",\r\n" + 
				"			\"jump\": \"/systemFlow/leaveManagement/index\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"福利房\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"我的福利房\",\r\n" + 
				"				\"jump\": \"/systemFlow/myWelfareHouse/myWelfareHouse\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"我的培训\",\r\n" + 
				"			\"jump\": \"/systemFlow/myTraining/myTraining\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"我的奖惩\",\r\n" + 
				"			\"jump\": \"/template/tips/404\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"theme\",\r\n" + 
				"			\"title\": \"离职申请\",\r\n" + 
				"			\"jump\": \"/personalCenter/employeeTurnover/employeeTurnover\"\r\n" + 
				"				\r\n" + 
				"				\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"name\": \"report forms\",\r\n" + 
				"		\"title\": \"报表中心\",\r\n" + 
				"		\"icon\": \"layui-icon-component\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"花名册图形\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"员工信息汇总\",\r\n" + 
				"				\"jump\": \"/rosterChart/staffInfo\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"黑名单\",\r\n" + 
				"				\"jump\": \"/rosterChart/blackListInfo\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"备用人才库\",\r\n" + 
				"				\"jump\": \"/rosterChart/reserveTalentPool\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"招聘\",\r\n" + 
				"				\"jump\": \"/rosterChart/recruitInfo\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"培训\",\r\n" + 
				"				\"jump\": \"/rosterChart/trainInfo\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"车辆图\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"全部车辆\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"新入库\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"客户需求图\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"需求总量\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"完成需求总量\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}, {\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"延时需求总量\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}]\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"违章图\",\r\n" + 
				"			\"list\": [{\r\n" + 
				"				\"name\": \"list\",\r\n" + 
				"				\"title\": \"违章记录\",\r\n" + 
				"				\"jump\": \"/template/tips/404\"\r\n" + 
				"			}]\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"name\": \"reportForms\",\r\n" + 
				"		\"title\": \"后台设置\",\r\n" + 
				"		\"icon\": \"layui-icon-component\",\r\n" + 
				"		\"list\": [{\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"小组权限\",\r\n" + 
				"			\"jump\": \"/reportForms/groupPermissions/index\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"人员分组\",\r\n" + 
				"			\"jump\": \"/template/tips/404\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"API(接口)配置\",\r\n" + 
				"			\"jump\": \"/reportForms/APIConfiguration/APIConfiguration\"\r\n" + 
				"		},{\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"参数配置\",\r\n" + 
				"			\"jump\": \"/template/tips/404\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"layer\",\r\n" + 
				"			\"title\": \"测试\",\r\n" + 
				"			\"jump\": \"/app/content/tags\"\r\n" + 
				"		}, {\r\n" + 
				"			\"name\": \"processManagement\",\r\n" + 
				"			\"title\": \"流程管理\",\r\n" + 
				"			\"jump\": \"/reportForms/processManagement/processManagement\"\r\n" + 
				"		}\r\n" + 
				"		]\r\n" + 
				"	}]\r\n" + 
				"}";
		
		Map<String, Object> json2Map = CommUtil.JSON2Map(str);
		List<Map<String,Object>> list = CommUtil.JSONList2JSONMap(json2Map.get("data"));
		for (Map<String, Object> map : list) {
			map.put("level", 1);
			Menu menu = getMenu(map);
			menuMapper.insert(menu);
			if(null != map.get("list")) {
				List<Map<String,Object>> level2List = CommUtil.JSONList2JSONMap(map.get("list"));
				for (Map<String, Object> map2 : level2List) {
					map2.put("level",2);
					map2.put("parentId", menu.getId());
					Menu menu2 = getMenu(map);
					menuMapper.insert(menu2);
					if(null != map2.get("list")) {
						List<Map<String,Object>> level3List = CommUtil.JSONList2JSONMap(map2.get("list"));
						for (Map<String, Object> map3 : level3List) {
							map3.put("level", 3);
							map3.put("parentId",menu2.getId());
							Menu menu3 = getMenu(map);
							menuMapper.insert(menu3);
						}
					}
				}
			}
		}
		
	}
	
	public Menu getMenu(Map<String,Object> parameters) {
		Menu menu = JSONObject.parseObject(JSONObject.toJSONString(parameters),Menu.class);
		return menu;
	}
	
	@Test
	public void testDataSource() {
		Menu menu = menuMapper.selectByPrimaryKey(642);
//		Menu menu2 = menuMapper2.selectByPrimaryKey(642);
//		System.err.println(menu.getName());
//		System.err.println(menu2.getName());
		
	}
	
	

}