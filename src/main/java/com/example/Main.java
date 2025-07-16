package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import com.google.common.collect.TreeMultimap;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("--------------------------------------------------");
        System.out.println("What do you want to do? 1) Swap Case");
        System.out.println(" 2) Compare Dates");
        System.out.println(" 3) reverse");
        System.out.println(" 4) sum until target");
        System.out.println(" 5) remove duplicates from array");
        System.out.println(" 6) is it a Palindrome?");
        System.out.println(" 7) rotate an array");
        Scanner scanner = new Scanner(System.in); // For reading from the console
        int inputChoice = scanner.nextInt();

        switch(inputChoice)   {
            case 1: 
                swapCase();
                break;
            case 2: 
                compareDates();
                break;

            case 3: 
                reverseString("aloha Baby ");
                break;
            case 4:
                sumTarget();
                break;
            case 5:
               int[] inputArray = {0, 1, 1, 4, 4, 4, 7, 7, 7, 8};
               int k = removeDuplicates(inputArray);
               break;
            case 6:
                String inputString = "A man, a plan, a canal: Panama";
                isPalindrome(inputString);
            case 7:
                int inputArray2[] = {1,2,3,4,5,6,7};
                rotate(inputArray2, 3);
        }
        scanner.close();    }

    static void swapCase() {
        System.out.println("-------------------Swap Case----------------------");
        System.out.println("Enter String with mixture of upper and lower case:");
        Scanner scanner = new Scanner(System.in); // For reading from the console
        String inputString = scanner.nextLine();
        char c;
        StringBuilder swapCase = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            c = inputString.charAt(i);
            if( Character.isUpperCase(c))   {
                swapCase.append(Character.toLowerCase(c));
            }else if (Character.isLowerCase(c)) {
                swapCase.append(Character.toUpperCase(c));
            }else{
                swapCase.append(c);
            }

        }
       System.out.println("-----------------------------------------");
       System.out.println(swapCase);       
       scanner.close();
    }


    static void compareDates() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2024-01-15 10:30:00";
        String dateString2 = "2024-01-15 12:00:00";
        Date date1 = sdf.parse(dateString1);
        Date date2 = sdf.parse(dateString2);

        System.out.println("date1 before date2: " + date1.before(date2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse("2024-01-15", formatter);
        LocalDate localDate2 = LocalDate.parse("2024-01-16", formatter);

        System.out.println("localDate1 before localDate2: " + localDate1.isBefore(localDate2));
    }

    static void reverseString(String reverseMe) {
        char[] reversedStr = new char[reverseMe.length()];
        for (int i = 0; i < reverseMe.length(); i++)   {
            reversedStr[i] = reverseMe.charAt(reverseMe.length()-i-1);
        }
        System.out.print(reversedStr);
    }

    /**
     * 
     */
    static void  sumTarget()    {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] answer = new int[2];
        int target = 15;
        int sum = 0;
        for (int i =0 ; i < inputArray.length; i++) {
        System.out.println("sum =" +sum);
        System.out.println("i =" +i);
            for(int j=i; j < inputArray.length-j; j++)  {
                System.out.println("j =" +j);
                sum += inputArray[j];
                if (sum == target)  {//continue adding since not yet target
                    answer[0]= i;
                    answer[1]= j;
                    System.out.println("From" + answer[0] +" to "+ answer[1]);
                    System.out.println(sum);
                    return;
                }
            }
        }
        
    }

/* Given an integer array nums sorted in non-decreasing order, remove the duplicates 
    in-place such that each unique element appears only once. The relative order of the elements 
    should be kept the same. Then return the number of unique elements in nums.
Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
Change the array nums such that the first k elements of nums contain the unique elements in the order they 
were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k. */
    static int removeDuplicates(int[] nums) {
        int i=0;//starting unique number pointer at 0
        for(int j=1;j<nums.length;j++){//Loop with index j starting from 1 up to nums.length - 1:
            if(nums[i]!=nums[j]){//if not the same as the previous number
                i++; //move the unique pointer i to the next position
                nums[i]=nums[j]; //assign Copy the new unique value from nums[j] to nums[i].
            }
        }
        for (int c: nums)   {System.out.print(c);}
        return i+1;
        
    }
/*A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all 
non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and 
numbers.
Given a string s, return true if it is a palindrome, or false otherwise. */
    static boolean isPalindrome(String inputString)  {
        Boolean answer = true;//true
        inputString = inputString.toLowerCase();
        System.out.println("Lower case: " + inputString);
        //StringBuilder cleanString = new StringBuilder();
        char[] cleanArray = new char[inputString.length()];
        int j = 0;
        for(int i =0; i < inputString.length();i++) {
           if(Character.isLetterOrDigit(inputString.charAt(i))) {
                //cleanString.append(inputString.charAt(i));
                cleanArray[j] = inputString.charAt(i);
                j++;
           }
        }
        System.out.println("Cleaned: " + String.valueOf(cleanArray));
        for(int i =0; i< j/2;i++) {
            if(cleanArray[i] != cleanArray[j-i-1]) {
                System.out.println("Not a palindrome");
                answer = false;
                break;
            }
        }
        //StringBuilder reversedString = new StringBuilder(cleanString).reverse();
        //System.out.println("Reversed: " + reversedString);
        //if(cleanString.toString().equals(reversedString.toString()))  {answer = true;}
        return answer;
    }

    /*Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.*/
        //Input: nums = [1,2,3,4,5,6,7], k = 3
//Output: [5,6,7,1,2,3,4]
//Explanation:
//rotate 1 steps to the right: [7,1,2,3,4,5,6]
//rotate 2 steps to the right: [6,7,1,2,3,4,5]
//rotate 3 steps to the right: [5,6,7,1,2,3,4]
    static void rotate(int[] nums, int k) {
        if (k > nums.length ) {k = k % nums.length;}
        int[] tempArr = new int[k];
        int[] tempArrF = new int[nums.length];
        if (nums.length > 1)    {
            System.out.print(nums.length);
            for (int i =0; i < k; i++) {//move to tempArr
                tempArr[i] = nums[nums.length - k + i];
            }
            for (int i =0; i < nums.length - k; i++) {//move to tempArrF
                tempArrF[i] = nums[i];
            }
            //move the front to the back
            for (int i =0; i < nums.length-k; i++) {//move to tempArr
                nums[i+k] = tempArrF[i];
            }
            //move the back to the front
            for (int i =0; i < k; i++) {//move to tempArr
                nums[i] = tempArr[i];
            }
        }//end if
    }

    /* You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.*/
    public int maxProfit(int[] prices) {
        int earnings;
        int maxEarnings = 0;
    for (int i =prices.length-1 ; i >= 0; i--)    {
        System.out.println("i: " + i);
        for (int j =i ; j >= 0; j--)    {
            System.out.println("j: " + j);
            earnings = prices[i] - prices[j];
            if(earnings > maxEarnings)  {maxEarnings = earnings;}
        }//end j
    }//end i
    return maxEarnings;
    }
}
   
