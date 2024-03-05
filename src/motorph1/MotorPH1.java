//Comprog1-MO-IT101-Group5
//By: Group 5 - Jeffrey Dollopac, Joyce Ferrer, Ryu Ken Lindo, Mikko Jerome Bautista, 


package motorph1;  // Package declaration

// Import necessary Java classes
import java.io.BufferedReader; //used for reading text from a character-input stream
import java.io.FileReader;     //used for reading files in the file system
import java.io.IOException;    //allows you to use the IOException class, which is an exception that is thrown when an I/O operation fails.
import java.time.Duration;     //duration of seconds or hours
import java.time.LocalTime;    //allows to use the LocalTime class which represents a time without a date or time zone
import java.util.Scanner;      //allows you to use the Scanner class, which is used for parsing primitive types and strings from the input stream.


public class MotorPH1 {
    //array of predefined usernames and passwords 
    private static final String[] usernames = {"user1", "user2", "user3"};
    private static final String[] passwords = {"123", "456", "789"};
    
    //attendance CSV file Path
    private static final String attendanceCsvFile = "C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Attendance Details.csv"; 
    private static final String csvSplitBy = ",";    //CSV file delimiter to separate the data by "," 

    public static void main(String[] args) {    // Main method
        // Try-with-resources to automatically close the input scanneror else will use scanner.close();
        try (Scanner scanner = new Scanner(System.in)) {
            String enteredUsername = "";
            String enteredPassword = "";
            
            // Infinite loop for login until correct credentials are entered
            while (true) {
                System.out.println("\nWELCOME TO MOTORPH");
                System.out.print("\nEnter username: ");
                enteredUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                enteredPassword = scanner.nextLine();

                int index = -1;
                for (int i = 0; i < usernames.length; i++) {    // Loop through predefined usernames and passwords
                    if (enteredUsername.equals(usernames[i]) && enteredPassword.equals(passwords[i])) {   // Check if username and password match
                        index = i;
                        break;
                    }
                }
                if (index != -1) {    
                    // Authentication successful

                    boolean backToMainMenu = false;
                    boolean logout = false;

                    do {    // Loop for Main Menu Options
                        System.out.println("\nMAIN MENU");
                        System.out.println("  Option [1] - Employee Personal Details");
                        System.out.println("  Option [2] - Employee Attendance");
                        System.out.println("  Option [3] - Employee Payroll Details");
                        System.out.println("  Option [4] - Logout");

                        System.out.print("\nPlease enter option: ");
                        int optionNumber = scanner.nextInt();
                        scanner.nextLine();

                        boolean repeatCurrentOption = false;
                        switch (optionNumber) {
                            case 1: // Option 1: Employee Personal Details
                                System.out.println("\nEmployee Personal Details:");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber = scanner.nextLine();
                                displayEmployeeDetails("C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Employee Details.csv", inputEmployeeNumber);
                                //displayEmployeeDetails method to call the code for Option 1 - Employee Details
                                break;
                            case 2: // Option 2: Employee Attendance
                                System.out.println("\nEmployee Attendance:");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber2 = scanner.nextLine();
                                System.out.print("Enter Month:");
                                String inputMonth = scanner.nextLine();
                                System.out.println("Note: Payroll/Attendance count starts every Monday");
                                System.out.print("Enter Week: (Format: dd-mmm-yy e.g. 03-jan-22):");
                                String inputWeek = scanner.nextLine();
                                displayEmployeeAttendance(inputEmployeeNumber2, inputMonth, inputWeek);
                                //displayEmployeeAttendance method to call the code for Option 2 - Attendance
                                break;
                            case 3: // Option 3: Employee Payroll Details
                                System.out.println("\nEmployee Payroll Details");
                                System.out.print("Enter Employee ID Number: ");
                                String inputEmployeeNumber3 = scanner.nextLine();
                                System.out.print("Enter Month:");
                                String inputMonth2 = scanner.nextLine();
                                System.out.println("Note: Payroll/Attendance count starts every Monday");
                                System.out.print("Enter Week: (Format: dd-mmm-yy e.g. 03-jan-22):");
                                String inputWeek2 = scanner.nextLine();
                                
                                displayEmployeeAttendancePayroll(inputEmployeeNumber3, inputMonth2, inputWeek2);
                                //displayEmployeeAttendancePayroll method to call the code for Option 3 - Payroll Details
                                break;
                            case 4: //Option 4: Logout
                                System.out.println("\nExiting program...");
                                logout = true; 
                                break;
                            default:  // Default case for invalid options
                                System.out.println("\nOption not valid. Please enter a valid option.");
                                repeatCurrentOption = true;
                                break;
                    }

                        if (repeatCurrentOption) {
                            continue; // Repeat the current switch case
                        }

                        if (!logout) {
                            System.out.print("\nBack to Main Menu? (Y/N): ");
                            String answer = scanner.nextLine();
                            backToMainMenu = answer.equalsIgnoreCase("Y");
                        }
                    } while (backToMainMenu && !logout); // Exit loop if user chooses "N" or if logout is true

                    // Ask for authentication variables after logout
                    enteredUsername = "";
                    enteredPassword = "";
                } else {
                    System.out.println("Wrong username/password. Please try again.");
                }
            }
        }
    }




    //-------------------OPTION 1 Data--------------------------------
    
    public static void displayEmployeeDetails(String csvFile, String employeeNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean employeeFound = false; // Flag to track if employee is found

            //set to 0 to initializes the variable with a default value, ensuring it has a starting value even if not set elsewhere.
            // Variables for salary details
            float basicSalary = 0;
            float riceSubsidy = 0;
            float phoneAllowance = 0;
            float clothingAllowance = 0;
            float grossSemiMonthRate = 0;
            float hourlyRate = 0;
            float minuteRate = 0;
      
            //Read and process CSV file
            while ((line = br.readLine()) != null) {  //to read each line from a file using a BufferedReader (br) until the end of the file is reached
                String[] employeeData = line.split(csvSplitBy);  // Split the line by comma

                //Check if the employee ID matches
                //This condition checks if the employeeData array has at least one element and ensures that there is data to be processed before attempting to access the first element of the array
                if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Display employee details
                System.out.println("\nPERSONAL DETAILS:");
                System.out.println("  Employee Number: " + employeeData[0]);
                System.out.println("  Last Name: " + employeeData[1]);
                System.out.println("  First Name: " + employeeData[2]);
                System.out.println("  Birthdate: " + employeeData[3]);
                System.out.println("  Address: " + employeeData[4]);
                System.out.println("  Contact No.: " + employeeData[5]);
                System.out.println("  SSS No.: " + employeeData[6]);
                System.out.println("  Philhealth No.: " + employeeData[7]);
                System.out.println("  TIN No.: " + employeeData[8]);
                System.out.println("  Pagibig No.: " + employeeData[9]);
                System.out.println("  Status: " + employeeData[10]);
                System.out.println("  Position: " + employeeData[11]);
                System.out.println("  Immediate Supervisor: " + employeeData[12]);
                    
                //parseFloat is used to convert a string to float    
                basicSalary = Float.parseFloat(employeeData[13]);
                riceSubsidy = Float.parseFloat(employeeData[14]);
                phoneAllowance = Float.parseFloat(employeeData[15]);
                clothingAllowance = Float.parseFloat(employeeData[16]);
                grossSemiMonthRate = Float.parseFloat(employeeData[17]);
                hourlyRate = Float.parseFloat(employeeData[18]);
                minuteRate = Float.parseFloat(employeeData[19]);    
                    
                //set a variable for Gross calculation  
                float gross = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;
                
                System.out.println("\nSALARY DETAILS:");
                System.out.printf("  Basic Salary: %.2f\n", basicSalary);
                System.out.printf("  Rice Subsidy: %.2f\n", riceSubsidy);
                System.out.printf("  Phone Allowance: %.2f\n", phoneAllowance);
                System.out.printf("  Clothing Allowance: %.2f\n", clothingAllowance);
                //System.out.println("  Gross Semi-monthly Rate: " + grossSemiMonthRate);
                System.out.printf("  Hourly Rate: %.2f\n", hourlyRate);
                System.out.printf("  GROSS SALARY w/ ALLOWANCE: %.2f\n", gross);   
                    
            
     
                    employeeFound = true; // Set flag to true
                    break; // Stop searching after finding the employee
                }
            }

            if (!employeeFound) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
          //IOException is an exception class that handles input-output related errors
          //try & catch block helps to preventthe program from crashing due to errors
          //the catch block is used to handle exceptions that might occur in the corresponding try block
          //catch (IOException e) is used to catch and handle errors that occur while reading or writing files in Java. When such an error happens, the program jumps to the catch block, where you can write code to deal with the error, like displaying a message to the user or logging the problem for later review  
        }
    }

    
    //-------------------OPTION 2 Data--------------------------------
    
public static void displayEmployeeAttendance(String employeeNumber, String month, String week) {
    try (BufferedReader br = new BufferedReader(new FileReader(attendanceCsvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found
        double totalWorkHours = 0; // Use double for decimal values
        double attendanceCount = 0; // Counter for attendance entries
        boolean nameDisplayed = false; // Flag to track if name is already displayed
        
        // Read and process attendance CSV file
        while ((line = br.readLine()) != null) {
            String[] attendanceData = line.split(csvSplitBy);

            // Check if the attendance record matches employee [0]ID,[7] month, and [8]week
            if (attendanceData.length > 9 && 
                attendanceData[0].trim().equals(employeeNumber.trim()) && 
                attendanceData[7].trim().equalsIgnoreCase(month.trim()) && 
                attendanceData[8].trim().equalsIgnoreCase(week.trim())) {
                
                // Display employee name only if not already displayed
                if (!nameDisplayed) {
                    System.out.println("\nEmployee Name: " + attendanceData[2] + " " + attendanceData[1]);
                    nameDisplayed = true; // Set flag to true after displaying name
                }
                
                // Increment attendance count to compute the days that the employee worked in a week
                attendanceCount++;
                
                // Display attendance details
                System.out.println("\nATTENDANCE DETAILS:");
                System.out.println("  Employee Number: " + attendanceData[0]);
                System.out.println("  Date: " + attendanceData[3]);
                System.out.println("  Time In: " + attendanceData[4]);
                System.out.println("  Time Out: " + attendanceData[5]);
                
                // Calculate work hours as decimal
                LocalTime timeIn = LocalTime.parse(attendanceData[4]);
                LocalTime timeOut = LocalTime.parse(attendanceData[5]);
                Duration duration = Duration.between(timeIn, timeOut);
                double decimalHours = duration.toMinutes() / 60.0 - 1; // Convert minutes to decimal hours
                //-1 to deduct the 1 hour lunch break
                
                // Apply rules for work hours calculation, grace period, absences, halfday
                if (decimalHours > 7.82) {
                    decimalHours = 8.0;
                } else if (decimalHours <= 0) {
                    decimalHours = 0.0;
                } else if (decimalHours <= 4) {
                    decimalHours += 1;
                }
                
                System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours);

                // Accumulate total work hours (shorthand of totalWorkHours = totalWorkHours + decimalHours;)
                totalWorkHours += decimalHours;

                employeeFound = true; // Set flag to true
            }
        }

        if (employeeFound) {
            //no. of days that the employee worked in a week x 8 hrs per day
            double AttendanceTotal = (attendanceCount * 8);
          
            System.out.println("\nTotal Attendance Entries:" + attendanceCount);
            System.out.printf("Total Hours Entries:%.2f hrs\n" , AttendanceTotal);
            System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
            
            //tardiness calculation
            double Tardiness = (AttendanceTotal - totalWorkHours);
            System.out.printf("Tardiness: %.2f hrs\n", Tardiness);

        } else {
            System.out.println("Attendance not found for the employee in the specified month and week.");
        }
    } catch (IOException e) {
        
    }
}


    //-------------------OPTION 3 Data--------------------------------

  public static void displayEmployeeAttendancePayroll(String employeeNumber, String month, String week) {
    try (BufferedReader br = new BufferedReader(new FileReader(attendanceCsvFile))) {
        String line;
        

        boolean employeeFound = false; // Flag to track if employee is found
        
        double totalWorkHours = 0; 
        double attendanceCount = 0; 

        float basicSalary = 0; 
        float riceSubsidy = 0; 
        float phoneAllowance = 0; 
        float clothingAllowance = 0; 
        float grossSemiMonthRate = 0; 
        float hourlyRate = 0; 
        float taxRate = 0; 
        float sssRate = 0; 
        float philhealthRate = 0;        
        float pagibigRate = 0;
        float tardiness = 0;
        
        String employeeLastName = "";
        String employeeFirstName = "";
     
     
        // Read and process attendance CSV file
        while ((line = br.readLine()) != null) {
            String[] attendanceData = line.split(csvSplitBy);

            // Check if the attendance record matches employee ID, month, and week
            if (attendanceData.length > 9 && 
                attendanceData[0].trim().equals(employeeNumber.trim()) && 
                attendanceData[7].trim().equalsIgnoreCase(month.trim()) && 
                attendanceData[8].trim().equalsIgnoreCase(week.trim())) {
                
                // Increment attendance count to compute the days that the employee worked in a week
                attendanceCount++;

                
                // Calculate work hours as decimal
                LocalTime timeIn = LocalTime.parse(attendanceData[4]);
                LocalTime timeOut = LocalTime.parse(attendanceData[5]);
                Duration duration = Duration.between(timeIn, timeOut);
                double decimalHours = duration.toMinutes() / 60.0 - 1; // Convert minutes to decimal hours
                //System.out.printf("  Total Work Hours This Day: %.2f hrs\n", decimalHours ) ;
               
                //Grace Period, absences and halfday
                if (decimalHours > 7.82) {
                decimalHours = 8.0;
                } else if (decimalHours <= 0) {
                 decimalHours = 0.0;
                } else if (decimalHours <= 4) {
                 decimalHours = decimalHours + 1;
                }

                // Accumulate total work hours
                totalWorkHours += decimalHours;

                
                // Retrieve hourly rate from the employee details
                String employeeCsvFile = "C:\\Users\\IT-Spare\\Documents\\NetBeansProjects\\MotorPH1\\src\\motorph1\\MotorPH Employee Details.csv";
                basicSalary = getbasicSalaryFromEmployeeDetails(employeeCsvFile, employeeNumber);
                riceSubsidy = getriceSubsidyFromEmployeeDetails(employeeCsvFile, employeeNumber);
                phoneAllowance = getphoneAllowanceFromEmployeeDetails(employeeCsvFile, employeeNumber);
                clothingAllowance = getclothingAllowanceFromEmployeeDetails(employeeCsvFile, employeeNumber);
                grossSemiMonthRate = getgrossSemiMonthRateFromEmployeeDetails(employeeCsvFile, employeeNumber);
                hourlyRate = gethourlyRateFromEmployeeDetails(employeeCsvFile, employeeNumber);
                
                
                employeeLastName = attendanceData[1].trim(); 
                employeeFirstName = attendanceData[2].trim(); 
                
                employeeFound = true; 
               //break;
            }
        }
                
             

        if (employeeFound) {
            if (totalWorkHours > 0) {
                float weeklyGross = hourlyRate * (float) totalWorkHours;
                float allowances = (riceSubsidy + phoneAllowance + clothingAllowance) / 4; 
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nEmployee Name: " + employeeFirstName + " " + employeeLastName);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("EMPLOYEE RATE : ");  
        System.out.println("Monthly Basic : " + basicSalary + ", Weekly Basic: " + basicSalary / 4);  
        System.out.println("Monthly RiceSubsidy: " + riceSubsidy + ", Weekly RiceSubsidy: " + riceSubsidy / 4);  
        System.out.println("Monthly Phone Allowance: " + phoneAllowance + ", Weekly Phone Allowance: " + phoneAllowance / 4);
        System.out.println("Monthly Clothing Allowance : " + clothingAllowance + ", Weekly Clothing Allowance : " + clothingAllowance / 4);
   
        //System.out.printf("Gross Semi-Month Rate : %.2f\n", grossSemiMonthRate);
    
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("EARNINGS:");
        System.out.printf("\nHourly Rate: %.2f hrs\n", hourlyRate);
        System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
        System.out.println("------------------------------------------------------------------------------");
    
        //%.2f in the printf to print 2 decimal amount
        System.out.printf("Weekly GROSS (HourlyRate * HoursWorkedForTheWeek): %.2f\n", weeklyGross); //%.2f in the printf to print 2 decimal amount
        System.out.printf("Weekly Allowances (riceSubsidy + phoneAllowance + clothingAllowance): %.2f\n", allowances);
                
                float earnings = (weeklyGross + (allowances));  // + allowances     
                System.out.printf("\n***TOTAL EARNINGS:%.2f\n", earnings);
                System.out.println("------------------------------------------------------------------------------");       
                // ----------------DEDUCTIONS EE SHARE----------------
                System.out.printf("DEDUCTIONS (Employee Deductions & EE Share):");
                
             
                //Tardiness
                double AttendanceTotal = (attendanceCount * 8);
                System.out.println("\nTotal Attendance Entries:" + attendanceCount);
                System.out.printf("Total Hours Entries:%.2f hrs\n" , AttendanceTotal);
                System.out.printf("Total Work Hours This Week: %.2f hrs\n", totalWorkHours);
                double Tardiness = (AttendanceTotal - totalWorkHours);
                System.out.printf("Tardiness: %.2f hrs \n", Tardiness);
                //System.out.println("Tardiness is already deducted from the Work Hours This Week");
             
                //Pagibig =(IF(basicSalary<=1500,(basicSalary*1%),(basicSalary*2%)))/4
                if (basicSalary <= 999) {
                pagibigRate = 0;
                } else if (basicSalary <= 1500) {
                pagibigRate = (float) (basicSalary * 0.01 / 4);
                } else if (basicSalary >= 1501) {
                pagibigRate = (float) (basicSalary * 0.02 / 4);
                }
                //Check if pagibigRate exceeds 100 after calculations
                if (pagibigRate >= 100) {
                pagibigRate = 25;
                }
                System.out.printf("\nPagibig Rate: %.2f\n", pagibigRate);
                
                
                //Philhealth            
                if (basicSalary > 0)   {
                philhealthRate = (float) (basicSalary * 0.03 / 4 / 2);
                }
                System.out.printf("Philhealth Rate: %.2f\n", philhealthRate);

                
                //SSS variable: sssRate
                if (basicSalary <= 21750) {
                sssRate = 990f / 4;
                } else if (basicSalary > 22250 && basicSalary <= 22750) {
                sssRate = 1012.50f / 4;
                } else if (basicSalary > 22750 && basicSalary <= 23250) {
                sssRate = 1035.00f / 4;
                } else if (basicSalary > 23250 && basicSalary <= 23750) {
                sssRate = 1057.50f /4;
                } else if (basicSalary > 23750 && basicSalary <= 24250) {
                sssRate = 1080.00f /4;
                } else if (basicSalary > 24250 && basicSalary <= 24750) {
                sssRate = 1102.50f /4;
                } else if (basicSalary < 24750) {
                sssRate = 1125.00f /4;
                }
                System.out.printf("SSS Rate: %.2f\n", sssRate);
                
                //Tax variable: taxRate
                if (basicSalary <= 20832) {
                taxRate = 0;
                } else if (basicSalary >= 20883 && basicSalary <= 33333) {
                taxRate = (float) (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 20833) * 0.20f / 4;
                } else if (basicSalary >= 33334 && basicSalary <= 66667) {
                taxRate = (float) (2500 + (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4) - 33333) * 0.25f) / 4;
                } else if (basicSalary >= 66668 && basicSalary <= 166667) {
                taxRate = (float) (10833 + (basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 66667) * 0.30f) / 4;
                } else if (basicSalary >= 166668 && basicSalary <= 666667) {
                taxRate = (float) (40833.33 + basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4) - 166667) * 0.32f / 4;
                } else if (basicSalary >= 666667) {
                taxRate = (float) (200833.33 + basicSalary - (sssRate * 4) - (pagibigRate * 4) - (philhealthRate * 4)- 666667) * 0.35f / 4;
                }
                System.out.printf("Tax Rate: %.2f\n", taxRate);
   
           
                // ----------------TOTAL DEDUCTIONS ER SHARE----------------
                System.out.printf("\nDEDUCTIONS (ER Employer Share):");
                System.out.printf("\nPaibig Rate: %.2f\n", pagibigRate );
                System.out.printf("Philhealth Rate: %.2f\n", philhealthRate);
                //System.out.printf("SSS Rate: %.2f\n", sssRate);
                
                // ----------------TOTAL DEDUCTIONS EE SHARE----------------
        
                float deductions = (pagibigRate + philhealthRate + taxRate + sssRate);  //+ sssRate + philhealthRate +  + taxRate;
                System.out.printf("\n***TOTAL EMPLOYEE DEDUCTIONS :%.2f\n", deductions);
                System.out.println("------------------------------------------------------------------------------");    
                // ----------------NetPay----------------
                float netPay = (earnings - deductions);  //+ sssRate + philhealthRate +  + taxRate;
                System.out.printf("\n***NET PAY :%.2f\n", netPay);   

            } else {
                System.out.println("Employee found but no attendance recorded for the specified month and week.");
            }
        } else {
            System.out.println("Employee not found or no attendance recorded for the specified month and week.");
        }
    } catch (IOException e) {
       
    }
}
  

    //-----------------To retrieve the Payroll Elements from the CSV file--------------------------------
  
public static float getbasicSalaryFromEmployeeDetails(String csvFile, String employeeNumber) {
    float basicSalary = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                basicSalary = Float.parseFloat(employeeData[13]); //column 13
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return basicSalary;
    } 
  
 
public static float getriceSubsidyFromEmployeeDetails(String csvFile, String employeeNumber) {
    float riceSubsidy = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                riceSubsidy = Float.parseFloat(employeeData[14]); // column 14
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return riceSubsidy;
    }


public static float getphoneAllowanceFromEmployeeDetails(String csvFile, String employeeNumber) {
    float phoneAllowance = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                phoneAllowance = Float.parseFloat(employeeData[15]); // column 15
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return phoneAllowance;
    }

public static float getclothingAllowanceFromEmployeeDetails(String csvFile, String employeeNumber) {
    float clothingAllowance = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                clothingAllowance = Float.parseFloat(employeeData[16]); // column 16
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return clothingAllowance;
    }



public static float getgrossSemiMonthRateFromEmployeeDetails(String csvFile, String employeeNumber) {
    float grossSemiMonthRate = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                grossSemiMonthRate = Float.parseFloat(employeeData[17]); //column 17
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return grossSemiMonthRate;
    }


public static float gethourlyRateFromEmployeeDetails(String csvFile, String employeeNumber) {
    float hourlyRate = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean employeeFound = false; // Flag to track if employee is found

        // Read and process CSV file
        while ((line = br.readLine()) != null) {
            String[] employeeData = line.split(csvSplitBy);

            // Check if the employee ID matches
            if (employeeData.length > 0 && employeeData[0].trim().equals(employeeNumber.trim())) {
                // Retrieve hourly rate
                hourlyRate = Float.parseFloat(employeeData[18]); //column 18
                employeeFound = true; // Set flag to true
                break; // Stop searching after finding the employee
            }
        }

        if (!employeeFound) {
            System.out.println("Employee not found in the employee details CSV file.");
        }
    } catch (IOException e) {
        
    }
    return hourlyRate;
    }
}