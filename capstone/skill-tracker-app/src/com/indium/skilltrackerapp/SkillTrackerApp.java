package com.indium.skilltrackerapp;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


import com.indium.skilltrackerapp.enums.SkillCategory;
import com.indium.skilltrackerapp.model.Associate;
import com.indium.skilltrackerapp.model.Skill;
import com.indium.skilltrackerapp.service.AssociateService;
import com.indium.skilltrackerapp.service.SkillService;
import com.indium.skilltrackerapp.serviceimpl.AssociateServiceImpl;
import com.indium.skilltrackerapp.serviceimpl.SkillServiceImpl;

public class SkillTrackerApp {

	static Scanner scanner = new Scanner(System.in);
	static AssociateService associateService = new AssociateServiceImpl();
	static SkillService skillService = new SkillServiceImpl();
	public static List<Skill> storeSkillTem = new ArrayList<Skill>();
 
//******************************main menu start**************************************************	
    public static void main(String[] args) {
    	 int choice;
        do {
            System.out.println("\nSkill Tracker App Menu: ");
            System.out.println("1. Add new Associate");
            System.out.println("2. List Associates");
            System.out.println("3. Edit Associate");
            System.out.println("4. Delete Associate");
            System.out.println("5. Search Associate");
            System.out.println("6. Show Key Metrics");
            System.out.println("7. Exit");

             choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNewAssociate();
                    break;
                case 2:
                    listAssociates();
                    break;
                case 3:
                    editAssociate();
                    break;
                case 4:
                    deleteAssociate();
                    break;
                case 5:
                    searchAssociate();
                    break;
                case 6:
                    showKeyMetrics();
                    break;
                case 7:
                    System.out.println("Thank you for using the Skill Tracker App. Goodbye!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }while(choice!=7);
    }
//********************************************************************************************    
//*****************************Add new Associate with skill**********************************
    private static void addNewAssociate() {
        System.out.println("Enter Associate Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Associate Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter Business Unit:");
        String businessUnit = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Location:");
        String location = scanner.nextLine();
     while (true) {
         System.out.println("Enter Skill (or type 'stop' to finish adding skills):");
         String skillName = scanner.nextLine();
         if (skillName.equalsIgnoreCase("stop")) {
            if(storeSkillTem.size()==0) {
            	System.out.println("Please enter atlist one skill");
            	continue;
            }
            break;
         }
         boolean skillExists = storeSkillTem.stream()
                 .anyMatch(skill -> skill.name().equalsIgnoreCase(skillName));
         if (skillExists) {
             System.out.println("Skill  already exists for this associate. Please enter another skill .");
             continue; 
         }
         System.out.println("Enter Skill Description:");
         String description = scanner.nextLine();

         System.out.println("Select Skill Category");
         System.out.println("1]Primary \n2]Secondary \nEnter your choice");
         int categoryChoice = scanner.nextInt();
         SkillCategory category = null ;
         if(categoryChoice == 1) {
        	 category = SkillCategory.Primary;
         }else {
        	 category = SkillCategory.Secondary;
         }
         System.out.println("Enter Skill Experience (in months):");
         int experience = scanner.nextInt();
         scanner.nextLine(); 
         Skill skill = new Skill(0, skillName, description, category, experience,0);
         storeSkillTem.add(skill);
     }
        Associate associate = new Associate(0, name, age, businessUnit, email, location, storeSkillTem, "", "");
        associateService.addAssociate(associate);
        System.out.println("Associate added successfully!");
    }
//**********************************************************************************************
//*******************************list All Associates********************************************* 
    private static void listAssociates() {
        List<Associate> associates = associateService.getAllAssociates();

        if (associates.isEmpty()) {
            System.out.println("No associates found.");
        } else {
        	printAssociates(associates);
        }
    }
//********************************************************************************************
//*************************************Edit associate******************************************
    @SuppressWarnings("unused")
	private static void editAssociate() {
        System.out.println("Enter the ID of the associate you want to edit:");
        int associateId = scanner.nextInt();
        scanner.nextLine();
        Associate associate = associateService.getAssociateById(associateId);
        String newName = associate.name(); int newAge = associate.age(); String newBusinessUnit = associate.businessUnit(); String newEmail = associate.email();  String newLocation = associate.location() ;
        if (associate != null) {
            while (true) {
                System.out.println("Editing Associate: " + associate.name());
                System.out.println("1. Edit Name");
                System.out.println("2. Edit Age");
                System.out.println("3. Edit Business Unit");
                System.out.println("4. Edit Email");
                System.out.println("5. Edit Location");
                System.out.println("6. view all skills ");
                System.out.println("7. Add Skill");
                System.out.println("8. Edit Skill");
                System.out.println("9. Delete Skill");
                System.out.println("10. Back to Main Menu");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the new name:");
                         newName = scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter the new age:");
                       newAge = scanner.nextInt();
                        break;
                    case 3:
                        System.out.println("Enter the new business unit:");
                         newBusinessUnit = scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Enter the new email:");
                        newEmail = scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Enter the new location:");
                         newLocation = scanner.nextLine();
                        break;
                    case 6:
                        viewAllSkillWithAssId(associate);
                        break;
                    case 7:
                        addSkillToAssociate(associate);
                        break;
                    case 8:
                        editSkillForAssociate(associate);
                        break;
                    case 9:
                        deleteSkillForAssociate(associate);
                        break;
                    case 10:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }if(choice==6) {
                	continue;
                }
                System.out.println("1]Continue Edit \n2]Submit \nEnter your choice");
                short editChoice = scanner.nextShort();
                if(editChoice==1) {
                	continue;
                }else if(editChoice==2) {
                	Associate updateAssociate = new Associate(associate.id(), newName, newAge, newBusinessUnit, newEmail, newLocation, null, "", "");
                  Boolean updated = associateService.updateAssociate(updateAssociate);
                  
                  if(updated) {
                	  System.out.println("Associate details updated successfully!");
                  }else {
                	  System.out.println("update failed");
                  }
                  return;
				}else {
					System.out.println("Invalid choice. continue fot edit.");
				}
            }
        } else {
            System.out.println("Associate not found with the given ID.");
        }
    }
//*********************************************************************************************
//****************************View All skills corresponding associate**********************    
    private static void viewAllSkillWithAssId(Associate associate) {
        List<Skill> skills = skillService.getSkillsByAssId(associate.id());

        if (skills.isEmpty()) {
            System.out.println("No skills found for this associate.");
        } else {
            System.out.println("Skills for Associate " + associate.name() + ":");
            System.out.println("+-----+----------------------+----------------------------------------+------------+-----------------+");
            System.out.println("| ID  |        Name          |            Description                 |  Category  |   Experience    |");
            System.out.println("+-----+----------------------+----------------------------------------+------------+-----------------+");

            for (Skill skill : skills) {
                System.out.printf("| %-3d | %-20s | %-38s | %-10s | %-15d months |\n",
                        skill.id(), skill.name(), skill.description(), skill.category(), skill.experience());
            }

            System.out.println("+-----+----------------------+----------------------------------------+------------+-----------------+");
        }
    }

//********************************************************************************************
 //*****************************Add Skill for associate*************************************   
    private static void addSkillToAssociate(Associate associate) {
    	 while (true) {
             System.out.println("Enter Skill (or type 'stop' to finish adding skills):");
             String skillName = scanner.nextLine();
             if (skillName.equalsIgnoreCase("stop")) {
                break;
             }
             boolean skillExists = associate.skills().stream()
                     .anyMatch(skill -> skill.name().equalsIgnoreCase(skillName));
             if (skillExists) {
                 System.out.println("Skill  already exists for this associate. Please enter another skill.");
                 continue;
             }
             System.out.println("Enter Skill Description:");
             String description = scanner.nextLine();

             System.out.println("Select Skill Category");
             System.out.println("1]Primary \n2]Secondary \nEnter your choice");
             int categoryChoice = scanner.nextInt();
             SkillCategory category = null ;
             if(categoryChoice == 1) {
            	 category = SkillCategory.Primary;
             }else {
            	 category = SkillCategory.Secondary;
             }
             System.out.println("Enter Skill Experience (in months):");
             int experience = scanner.nextInt();
             scanner.nextLine(); 
             Skill skill = new Skill(0, skillName, description, category, experience,associate.id());
           boolean addSkill = skillService.addSkill(skill, associate.id());
           if(addSkill) {
        	   System.out.println("skill added successfully");
           }else {
        	   System.out.println("adding skill failed");
           }
         } 
    }
//******************************************************************************************
 //****************************Edit Skill for Associate****************************************  
    private static void editSkillForAssociate(Associate ass) {
    	 System.out.println("Enter the skill's ID to edit:");
         int idToEdit = scanner.nextInt();
         scanner.nextLine();
       Skill skill =  skillService.getSkillById(idToEdit,ass.id());
       if(skill!=null) {
    	   System.out.println(skill.name());
           System.out.println("Enter Skill Description:");
           String description = scanner.nextLine();
           
           System.out.println("Select Skill Category");
           System.out.println("1]Primary \n2]Secondary \nEnter your choice");
           int categoryChoice = scanner.nextInt();
           SkillCategory category = null ;
           if(categoryChoice == 1) {
          	 category = SkillCategory.Primary;
           }else {
          	 category = SkillCategory.Secondary;
           }
           System.out.println("Enter Skill Experience (in months):");
           int experience = scanner.nextInt();
           scanner.nextLine(); 
           Skill updateSkill = new Skill(skill.id(), skill.name(), description, category, experience,ass.id());
          boolean updatesSkill = skillService.updateSkill(updateSkill, idToEdit);
          if(updatesSkill) {
        	  System.out.println("Skill updated successfully");
          }else {
        	  System.out.println("Skill update failed");
          }
       }else {
    	   System.out.println("Skill not found");
       }
       
    }
//************************************************************************************************
 //*************************Delete Skill for associate********************************************   
    private static void deleteSkillForAssociate(Associate ass) {
    	System.out.println("Enter the skill's ID to delete:");
        int idToDelete = scanner.nextInt();
        List<Skill> skills = skillService.getSkillsByAssId(ass.id());
        if(skills.size()==1) {
        	 System.out.println("This is the last skill for the associate. You cannot delete it.");
        }else {
        	boolean skillDelete = skillService.deleteSkill(idToDelete,ass.id());
        	if(skillDelete) {
        		System.out.println("successfully deleted");
        	}else {
        		System.out.println("Skill not found");
        	}
        }
    }
//****************************************************************************************    
//*****************************Delete Associate with skills*******************************
    private static void deleteAssociate() {
    	System.out.println("Enter the associates's ID to delete:");
        int idToDelete = scanner.nextInt();
      boolean deleteAllSkillForAss=  skillService.deleteSkillWithAssId(idToDelete);
      if(deleteAllSkillForAss) {
    	boolean deleteAss =  associateService.deleteAssociate(idToDelete);
    	if(deleteAss) {
    		System.out.println("Asscociate deleted successfully");
    	}
      }else {
    	  System.out.println("Failed");
      }
     }
//********************************************************************************************
//***************************Search Associate*************************************************   
    private static void searchAssociate() {
    	
    	while(true) {
    		 System.out.println("Search Options:");
    	        System.out.println("1. Search by Name");
    	        System.out.println("2. Search by ID");
    	        System.out.println("3. Search by Location");
    	        System.out.println("4. Search by Skill");
    	        System.out.println("5. Back to Main Menu");

    	        int choice = scanner.nextInt();
    	        scanner.nextLine(); // Consume newline

    	        switch (choice) {
    	            case 1:
    	                System.out.println("Enter the associate's name to search:");
    	                String nameToSearch = scanner.nextLine();
    	                searchAssociatesByName(nameToSearch);
    	                break;
    	            case 2:
    	                System.out.println("Enter the associate's ID to search:");
    	                int idToSearch = scanner.nextInt();
    	                searchAssociatesById(idToSearch);
    	                break;
    	            case 3:
    	                System.out.println("Enter the location to search:");
    	                String locationToSearch = scanner.nextLine();
    	                searchAssociatesByLocation(locationToSearch);
    	                break;
    	            case 4:
    	            	System.out.println("Enter the skill to search :");
    	                String skillsToSearch = scanner.nextLine();
    	                searchAssociatesBySkills(skillsToSearch);
    	                break;
    	            case 5:
    	                // Return to the main menu
    	                return;
    	            default:
    	                System.out.println("Invalid choice. Please try again.");
    	                break;
    	        }
    	}
       
    }
//****************************************************************************************
//******************************Search Associates By Name**********************************    
    private static void searchAssociatesByName(String nameToSearch) {
        List<Associate> associates = associateService.searchAssociatesByName(nameToSearch);
        if (associates.isEmpty()) {
            System.out.println("No associates found with the given name.");
        } else {
            System.out.println("Associates with the name '" + nameToSearch + "':");
            printAssociates(associates);
        }
    }
//**********************************************************************************************
 //*****************************Search Associates By Id******************************************   
    private static void searchAssociatesById(int idToSearch) {
        Associate associate = associateService.getAssociateById(idToSearch);
        if (associate == null) {
            System.out.println("No associate found with the given ID.");
        } else {
            System.out.println("Associate with ID " + idToSearch + ":");
            printAssociate(associate);
        }
    }
//*********************************************************************************************
//**************************Search Associates By Location**************************************   
    private static void searchAssociatesByLocation(String locationToSearch) {
        List<Associate> associates = associateService.searchAssociatesByLocation(locationToSearch);

        if (associates.isEmpty()) {
            System.out.println("No associates found in the location '" + locationToSearch + "'.");
        } else {
            System.out.println("Associates in the location '" + locationToSearch + "':");
            printAssociates(associates);
        }
    }
//******************************************************************************************
//*************************************Search Associates By Skills****************************    
    private static void searchAssociatesBySkills(String skillsToSearch) {
    	    List<Associate> associates = associateService.searchAssociatesBySkills(skillsToSearch);

    	    if (associates.isEmpty()) {
    	        System.out.println("No associates found with the specified skills.");
    	    } else {
    	        System.out.println("Associates with the specified skills:");
    	        printAssociates(associates);
    	    }
    }
//*******************************************************************************************    
//**********************************show key metrics********************************************
    private static void showKeyMetrics() {
        
        int choice;

        do {
            System.out.println("\nKey Metrics Options:");
            System.out.println("1. Total Number of Associates");
            System.out.println("2. Associates with More Than N Skills");
            System.out.println("3. List of Associate IDs with More Than N Skills");
            System.out.println("4. Total Number of Associates with Specific Skills");
            System.out.println("5. Associate-wise Skill Count (Primary and Secondary)");
            System.out.println("6. Business Unit-wise Associate Count");
            System.out.println("7. Location-wise skill Count");
            System.out.println("8. Skill-wise Associate Count");
            System.out.println("9. Skill-wise Average Associate Experience");
            System.out.println("10. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                   int count = associateService.getTotalAssociateCount();
                   System.out.println("\nTotal Number of Associates: "+count);
                    break;
                case 2:
                   System.out.println("Enter number");
                   int n = scanner.nextInt();
                 int nCount =  associateService.getTotalAssociatesWithSkillsCount(n);
                 System.out.println("\nAssociates with More Than "+n+" Skills: "+nCount);
                    break;
				case 3:
					System.out.println("Enter number");
					int nId = scanner.nextInt();
					List<Integer> assIds = associateService.getAssociateIdsWithSkillsCount(nId);
					System.out.println("Associate IDs with at least " + nId + " skills:");
					for (Integer assId : assIds) {
						System.out.println(assId);
					}
                    break;
                case 4:
                	System.out.println("Enter skill");
                	String skill= scanner.next();
                	scanner.nextLine();
                   int totalAssWithGivenSkill = associateService.getTotalAssociatesWithGivenSkills(skill);
                   System.out.println("Total Number of Associates with Specific Skills: "+totalAssWithGivenSkill);
                    break;
                case 5:
                    List<Object[]> associateWiseSkillCount = associateService.getAssociateWiseSkillCount();
                    System.out.println("Associate Wise Skill Count:");
                    System.out.printf("%-15s | %-20s | %-30s | %-30s\n", "Associate ID", "Associate Name", "Primary Skills", "Secondary Skills");

                    for (Object[] row : associateWiseSkillCount) {
                        System.out.printf("%-15s | %-20s | %-30s | %-30s\n", row[0], row[1], row[2], row[3]);
                    }
                    break;
                case 6:
                    List<Object[]> businessUnitwiseAssociateCount = associateService.getBusinessUnitWiseAssociateCount();
                    System.out.println("Business Unit Wise Associate Count:");
                    for (Object[] row : businessUnitwiseAssociateCount) {
                        for (Object element : row) {
                            System.out.print(element + "\t");
                        }
                        System.out.println(); 
                    }
                    break;
                case 7:
                    List<Object[]> locationwiseAssociteCount = associateService.getLocationWiseSkillCount();
                    System.out.println("Location Wise Associate Count:");
                    System.out.println("Location\tSkill \t Count");
                    for (Object[] row : locationwiseAssociteCount) {
                        String location = (String) row[0];
                        Map<String, Integer> skillCounts = (Map<String, Integer>) row[1];
                        System.out.print(location + "\t");
                        // Print skill count for each location
                        for (Map.Entry<String, Integer> entry : skillCounts.entrySet()) {
                            String sk = entry.getKey();
                            int skCount = entry.getValue();
                            System.out.print("\t"+sk + "\t" + skCount + " ");
                        }

                        System.out.println(); 
                    }
                    break;
                case 8:
                    List<Object[]> skillwiseAssociateCount = associateService.getSkillWiseAssociateCount();
                    System.out.println("Skill Wise Associate Count:");
                    for (Object[] row : skillwiseAssociateCount) {
                        for (Object element : row) {
                            System.out.print(element + "\t");
                        }
                        System.out.println(); 
                    }
                    break;
                
                case 9:
                   List<Object[]> skillWAvgAssoExp = associateService.getSkillWiseAvgAssociateExperience();
                   System.out.println("Skill Wise Average Associate Experience:");
                   for (Object[] row : skillWAvgAssoExp) {
                       for (Object element : row) {
                           System.out.print(element + "\t");
                       }
                       System.out.println(); 
                   }
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 10);
    }
//*********************************************************************************************
//******************************Print Methods***************************************************    
    private static void printAssociates(List<Associate> associates) {
        for (Associate associate : associates) {
            printAssociate(associate);
            System.out.println("----------------------------------------");
        }
    }
    private static void printAssociate(Associate associate) {
        System.out.println("ID: " + associate.id());
        System.out.println("Name: " + associate.name());
        System.out.println("Age: " + associate.age());
        System.out.println("Business Unit: " + associate.businessUnit());
        System.out.println("Email: " + associate.email());
        System.out.println("Location: " + associate.location());
        System.out.println("Create Time: " + associate.createTime());
        System.out.println("Update Time: " + associate.updateTime());

        
        List<Skill> skills = associate.skills();
        if (skills != null && !skills.isEmpty()) {
            System.out.println("Skills:");
            for (Skill skill : skills) {
                System.out.println("  Skill ID: " + skill.id());
                System.out.println("  Skill Name: " + skill.name());
                System.out.println("  Skill Description: " + skill.description());
                System.out.println("  Skill Category: " + skill.category());
                System.out.println("  Skill Experience: " + skill.experience() + " months");
            }
        } else {
            System.out.println("No skills found for this associate.");
        }
    }
}
