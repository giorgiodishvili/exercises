package com.george;

import java.util.Arrays;
import java.util.Stack;

public class Exercises {
    public boolean isPalindrome(String text){
        int start = 0;
        int textLength = text.length() - 1;

        while(start<textLength){
            if(text.charAt(start) != text.charAt(textLength)){
                return false;
            }
            start++;
            textLength--;
        }
        return true;
    }

    public int minSplit(int amount){
        int[] coins = {1,5,10,20,50};
        int i = coins.length;
        int result = 0;
        while(amount>0){
            result += amount / coins[i-1];
            amount %= coins[i-1];
            i--;
        }

        return result;
    }

    public int notContainsV2(int[] array){
        int searchedValue = 1;

        while(true){
            int finalSearchedValue = searchedValue;
            boolean b = Arrays.stream(array).anyMatch(x -> x == finalSearchedValue);
            if(!b){
                return searchedValue;
            }
            searchedValue++;
        }
    }

    public int notContains(int[] array){

        boolean acceptable = true;
        int searchedValue = 1;

        while(acceptable){
            for(int i : array){
                if(i==searchedValue){
                    acceptable = false;
                    break;
                }
            }

            if(acceptable){
                break;
            }
            acceptable = true;
            searchedValue++;
        }


        return searchedValue;

    }

    public boolean isProperly(String sequence){
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if ( c == '(' ) st.push(c);
            if ( c == ')' ) {

                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }
        return st.isEmpty();
    }

    public int countVariants(int stairsCount){
        int previousNumber = 0;
        int nextNumber = 1;

        for (int i = 1; i <= stairsCount; ++i)
        {

            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
        return nextNumber;
    }
}
