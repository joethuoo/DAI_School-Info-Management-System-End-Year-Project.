package com.school.userInterface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class InputValidator {

}

class StringValidator extends DocumentFilter
{   
    @Override
    public void insertString(DocumentFilter.FilterBypass fp, int offset, String s,
    		AttributeSet aset)throws BadLocationException{
    	
        int len = s.length();
        boolean isString = false;

        for (int i = 0; i < len; i++)
        {
            if (!Character.isDigit(s.charAt(i)))
            {
            	isString = true;
                break;
            }
        }
        if (isString)
            super.insertString(fp, offset, s, aset);
        else{
        	// System.out.println("This is Not a number insertString");
         // Warn the User here
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fp, int offset
                    , int length, String s, AttributeSet aset)
                                        throws BadLocationException
    {
        int len = s.length();
        boolean isString = false;

        for (int i = 0; i < len; i++)
        {
            if (!Character.isDigit(s.charAt(i)))
            {
            	isString = true;
                break;
            }
        }
        if (isString)
            super.replace(fp, offset, length, s, aset);
        else{
        	// Warn the User here
        	 //System.out.println("This is Not a number replace");
        }
    }
}

class IntegerValidator extends DocumentFilter
{   
    @Override
    public void insertString(DocumentFilter.FilterBypass fp, int offset, String s,
    		AttributeSet aset)throws BadLocationException{
    	
        int len = s.length();
        boolean isNumber = true;

        for (int i = 0; i < len; i++)
        {
            if (!Character.isDigit(s.charAt(i)))
            {
            	isNumber = false;
                break;
            }
        }
        if (isNumber)
            super.insertString(fp, offset, s, aset);
        else{
        	// System.out.println("This is Not a number insert String");
         // Warn the User here
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fp, int offset
                    , int length, String s, AttributeSet aset)
                                        throws BadLocationException
    {
        int len = s.length();
        boolean isNumber = true;

        for (int i = 0; i < len; i++)
        {
            if (!Character.isDigit(s.charAt(i)))
            {
            	isNumber = false;
                break;
            }
        }
        if (isNumber)
            super.replace(fp, offset, length, s, aset);
        else{
        	// Warn the User here
        	// System.out.println("This is Not a number replace");
        }
    }
}