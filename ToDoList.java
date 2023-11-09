import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean premiumPlan = false;
        String[] toDoList;
        ArrayList<String> deletedTasks = new ArrayList<String>();


        if (!premiumPlan) {
            System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\nYou are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            toDoList = new String[10];
        } else {
            toDoList = new String[30];
        }

        int userChoice = 0;

        do {

            int count = 0;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    count++;
                }
            }


            System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + count + "\u001b[38;5;15m free spaces on the list!\u001b[0m");


//            System.out.println("\n\u001b[38;5;15m " + Arrays.toString(toDoList) + "\u001b[0m");

            System.out.println("\n\u001b[38;5;15m1 - Show ToDoList\u001b[0m");
            System.out.println("\u001b[38;5;15m2 - Create task\u001b[0m");
            System.out.println("\u001b[38;5;15m3 - Mark as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m4 - Remove as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m5 - Edit task\u001b[0m");
            System.out.println("\u001b[38;5;15m6 - Delete task\u001b[0m");
            System.out.println("\u001b[38;5;15m7 - Retrieve task\u001b[0m");
            System.out.println("\u001b[38;5;15m8 - Organize alphabetically\u001b[0m");
            System.out.println("\u001b[38;5;15m9 - Upgrade ToDoList Plan\u001b[0m");
            System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
            System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
            userChoice = scan.nextInt();

            scan.nextLine();

            switch (userChoice) {
                case 1:
                    showToDoList(toDoList);
                    break;
                case 2:
                    createTask(toDoList);
                    break;
                case 3:
                    markTaskAsCompleted(toDoList);
                    break;
                case 4:
                    removeTaskAsCompleted(toDoList);
                    break;
                case 5:
                    editTask(toDoList);
                    break;
                case 6:
                    deleteTask(toDoList, deletedTasks);
                    break;
                case 7:
                    retrieveTask(toDoList,deletedTasks);
                    break;
                case 8:
                    organizeAlphabetically(toDoList);
                    break;
                case 9:
                    premiumPlan = upgradeToDoListPlan(toDoList, premiumPlan);
                    if (premiumPlan) {
                        String[] tempToDoList = new String[30];

                        for (int i = 0; i < toDoList.length; i++) {
                            if (i < tempToDoList.length) {
                                tempToDoList[i] = toDoList[i];
                            } else {
                                break;
                            }
                        }

                        toDoList = tempToDoList;
                    }
                    break;
                case 0:
                    System.out.println("\n\u001b[38;5;9mClosing Gracefully ToDoList program...\u001b[0m");
                    break;
                default:
                    System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                    break;
            }

        } while (userChoice != 0);
    }

    public static void showToDoList(String[] toDoList) {

        int count = 0;
        int completedCount = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
                if (toDoList[i].contains(" ✅")) {
                    completedCount++;

                }
            }
        }

        double taskCompletionPercentage = 0.0;
        if (count > 0) {
            taskCompletionPercentage = ((double) completedCount/count) * 100;
        }

        System.out.println("\n\u001b[38;5;15m Task amount is: \u001b[0m" + count);
        System.out.println("\n\u001b[38;5;15m Task completion percentage is: \u001b[0m" + taskCompletionPercentage + "%");


        if (count > 0) {
            System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] != null) {
                    if (toDoList[i].contains(" ✅")) {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;40m" + toDoList[i] + "\u001b[0m");
                    } else {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;1m" + toDoList[i] + "\u001b[0m");
                    }
                }
            }
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            /*for (int i = 0; i < toDoList.length; i++) { //remove auto
                if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                    toDoList[i] = null;
                }
            }*/

            int currentIndex = 0;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    for (int j = i + 1; j < toDoList.length; j++) {
                        if (toDoList[j] != null) {
                            toDoList[i] = toDoList[j];
                            toDoList[j] = null;
                            currentIndex = i + 1;
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }
    }

    public static void createTask(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        String userNewTask = scan.nextLine().trim();
        System.out.println("\n\u001b[38;5;15mNotes : \u001b[0m");
        String userNewTaskNotes = scan.nextLine() + " Created on " +  LocalDate.now() + " "+ LocalTime.now();

        if (!userNewTask.isEmpty()) {
            boolean added = false;

            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = userNewTask + " Notes: " + userNewTaskNotes;

                    System.out.print("\n\u001b[38;5;10mThe task \u001b[38;5;15m" + userNewTask +
                            " \n\u001b[38;5;10mNotes : \u001b[38;5;15m" + userNewTaskNotes + " \u001b[38;5;10m was created!\u001b[0m");
                    added = true;
                    break;
                }
            }

            if (!added) {
                System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
            }
        } else {
            System.out.println("\n\n\u001b[38;5;9mTask name cannot be empty.\u001b[0m");
        }
    }

    public static void markTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoiceOfTaskToMarkAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToMarkAsCompleted] != null) {
                if (toDoList[userChoiceOfTaskToMarkAsCompleted].contains(" ✅")) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    String completedTask = toDoList[userChoiceOfTaskToMarkAsCompleted];
                    toDoList[userChoiceOfTaskToMarkAsCompleted] = null;
                    for (int i = userChoiceOfTaskToMarkAsCompleted; i > 0; i--) {
                        toDoList[i] = toDoList[i - 1];
                    }
                    toDoList[0] = completedTask.concat(" ✅");
                    System.out.println("\n\u001b[38;5;10mTask successfuly marked as completed!\u001b[0m");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks!\u001b[0m");
        }
    }

    public static void removeTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int existsCompletedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                System.out.println(i + " - " + toDoList[i]);
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToRemoveAsCompleted] != null) {
                toDoList[userChoiceOfTaskToRemoveAsCompleted] = toDoList[userChoiceOfTaskToRemoveAsCompleted].replace(" ✅", "");
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
        }
    }

    public static void editTask(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoiceOfTaskToEdit = scan.nextInt();

            scan.nextLine();

            if (toDoList[userChoiceOfTaskToEdit] != null) {

                if (toDoList[userChoiceOfTaskToEdit].contains(" ✅")) {
                    toDoList[userChoiceOfTaskToEdit] = toDoList[userChoiceOfTaskToEdit].replace(" ✅", "");
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();
                    System.out.println("\u001b[38;5;15mNew notes: \u001b[0m");
                    String userNewTaskNotes = scan.nextLine();

                    System.out.println("\n\u001b[38;5;10mThe task \u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] +
                            "\u001b[38;5;10m was changed to \u001b[38;5;15m" + userEditTask +
                            " \n\u001b[38;5;10mNotes : \u001b[38;5;15m"
                            + userNewTaskNotes + "\u001b[38;5;10m!");
                    userEditTask = userEditTask.concat(" ✅");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask + " Notes: " + userNewTaskNotes;
                } else {
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();
                    System.out.println("\u001b[38;5;15mNew notes: \u001b[0m");
                    String userNewTaskNotes = scan.nextLine() + " Modified on " +  LocalDate.now() + " "+ LocalTime.now();

                    System.out.println("\n\u001b[38;5;10mThe task \u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] +
                            "\u001b[38;5;10m was changed to \u001b[38;5;15m" + userEditTask + " \n\u001b[38;5;10mNotes : \u001b[38;5;15m" +
                            userNewTaskNotes + "\u001b[38;5;10m!");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask + " Notes: " + userNewTaskNotes;
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks to edit!\u001b[0m");
        }
    }

    public static void deleteTask(String[] toDoList, ArrayList<String> deletedTasks) {
        Scanner scan = new Scanner(System.in);

        int existentTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                existentTasks++;
            }
        }

        if (existentTasks > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt() ;

            if (userChoiceOfTaskToDelete >= 0 && userChoiceOfTaskToDelete < toDoList.length) {
                if (toDoList[userChoiceOfTaskToDelete] != null) {
                    System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToDelete] + "\u001b[38;5;10m' was successfully deleted!\u001b[0m"+ " Deleted on " +  LocalDate.now() + " "+ LocalTime.now());
                    deletedTasks.add(toDoList[userChoiceOfTaskToDelete]);
                    toDoList[userChoiceOfTaskToDelete] = null;
                } else {
                    System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
                }
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    public static void retrieveTask (String[] toDoList,ArrayList<String> deletedTasks) {
        Scanner scan = new Scanner(System.in);
        if (deletedTasks.isEmpty()) {
            System.out.println("\u001b[38;5;9mNo deleted tasks to retrieve!\u001b[0m");
        } else {
            System.out.println("\u001b[38;5;15mDeleted Tasks:\u001b[0m");
            for (int i = 0; i < deletedTasks.size(); i++) {
                System.out.println(i + " - " + deletedTasks.get(i));
            }

            System.out.print("\n\u001b[38;5;15mChoose a task to retrieve: \u001b[0m");
            int userChoiceOfTaskToRetrieve = scan.nextInt();

            if (userChoiceOfTaskToRetrieve >= 0 && userChoiceOfTaskToRetrieve < deletedTasks.size()) {
                String retrievedTask = deletedTasks.remove(userChoiceOfTaskToRetrieve);
                for (int i = 0; i < toDoList.length ; i++) {
                    if(toDoList[i] == null){
                        toDoList[i] = retrievedTask;
                        break;
                    }
                }
                System.out.println("\u001b[38;5;10mRetrieved task: \u001b[38;5;15m" + retrievedTask + "\u001b[38;5;10m\u001b[0m");
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        }
    }

    public static void organizeAlphabetically(String[] toDoList) {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        Arrays.sort(toDoList, 0, count);
    }

    public static boolean upgradeToDoListPlan(String[] toDoList, boolean premium) {
        Scanner scan = new Scanner(System.in);
        if (!premium) {
            System.out.println("\n\u001b[38;5;15mDo you want to buy Premium Plan? (yes or no)\u001b[0m");
            System.out.print("\u001b[38;5;15m> \u001b[0m");
            String userUpgradeOption = scan.next();

            switch (userUpgradeOption) {
                case "yes":
                    premium = true;
                    System.out.println("\n\u001b[38;5;10mCurrently plan setted to Premium! Thank you!\u001b[0m");
                    break;
                default:
                    premium = false;
                    System.out.println("\n\u001b[38;5;12mMaybe next time then...\u001b[0m");
                    break;
            }

            return premium;
        } else {
            System.out.println("\n\u001b[38;5;11mYour plan is already setted to Premium! You don't need to buy it again.\u001b[0m");
        }
        return premium;
    }

}