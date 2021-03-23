package com.example.calculator;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.*;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        Spinner mySpinner=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adap=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
        		getResources().getStringArray(R.array.brackets));//(context,resourceFile,data)
        //setting to dropDown menu
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adap);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use postion value
            	String op="";

                switch (position)

                {

                    case 0:

                        op = "";
                        break;
                    case 1:
                        op = "(";
                        break;
                    case 2:
                        op = ")";
                        break;

                }
                TextView display=(TextView) findViewById(R.id.textView1);
                display.append(op);
                

            }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				}

        });
        
        return true;
    }
    public void clear(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.setText("");
    }
    public void del(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	CharSequence val=display.getText();
    	val=val.subSequence(0,val.length()- 1);
    	display.setText(val);
    	
    }
    public void button_0(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("0");
    }
    
    public void button_1(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("1");
    }
    public void button_2(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("2");
    	
    }
    public void button_3(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("3");
    }
    public void button_4(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("4");
    }
    public void button_5(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("5");
    }
    public void button_6(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("6");
    }
    public void button_7(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("7");
    } 
    public void button_8(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("8");
    } 
    public void button_9(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("9");
    }
    public void plus_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("+");
    }
    public void minus_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("-");
    }
    public void mul_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("x");
    }
    public void div_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("÷");
    }
    public void per_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append("%");
    }
    public void dot_button(View view)
    {
    	TextView display=(TextView) findViewById(R.id.textView1);
    	display.append(".");
    }
    public int precedence(char ele)
    {
    	switch(ele)
    	{
    		//case '(':return 4;
    				 
    		case 'x':
    		case '÷':return 3;
    				 
    		case '+':
    		case '-':return 2;
    				 
    		default:return 1;
    		
    	}
    }
    public String infpos(CharSequence exp)
    {
    	String res="";
    	Stack<Character> stack=new Stack<Character>();
    	//System.out.println(exp.length());
    	for(int i=0;i<exp.length();i++)
    	{
    		//System.out.println("abd");
    		String num="";
    		char ele=exp.charAt(i);
    		//System.out.println(i);
    		//System.out.println(ele);
    		if(Character.isDigit(ele))
    		{
    			while(Character.isDigit(ele)||ele=='.')
    			{
    				num+=ele;
    				i++;
    				if(i<exp.length())
    					ele=exp.charAt(i);
    				else
    					break;
    				//System.out.println(ele);
    			}
    			//System.out.println(num);
    			res+=num+',';
    			//System.out.println(res);
    			i--;
    			//System.out.println(i);
    		}
    		else if(ele=='(')
    		{
    			stack.push(ele);
    		}
    		else if (ele == ')') 
            { 
                while ((!stack.isEmpty()) &&(stack.peek() != '(') )
                    res += stack.pop(); 
                  
                    stack.pop(); 
            } 
    		else
    		{
    			//System.out.println("abd");
    			//System.out.println(stack.isEmpty());
    			while(!stack.isEmpty() && precedence(ele)<=precedence(stack.peek()))
    			{
    				res+=stack.pop();
    			}
    			stack.push(ele);
    		}
    		
    	}
    	//System.out.println(stack.isEmpty());
    	while(!stack.isEmpty())
    	{
    		if(stack.peek()=='(')
    			return "invalid expression"; 
    		//System.out.println(stack.pop());
    		res+=stack.pop();
    	}
    	return res;
    }
    public float evaluatePostfix(String pos) 
    { 
        Stack<Float> stack=new Stack<Float>(); 
        //System.out.println(pos.length()); 
        
        for(int i=0;i<pos.length();i++) 
        { 
        	String num="";
            char c=pos.charAt(i); 
            if(Character.isDigit(c)) 
            {
            	while(c!=',')
    			{
    				num+=c;
    				i++;
    				if(i<pos.length())
    					c=pos.charAt(i);
    				else
    					break;
    				//System.out.println(ele);
    			}
            	//i--;
            	//System.out.println(num);
            	stack.push(Float.parseFloat(num));
            }
            else
            { 
                float val1 = stack.pop(); 
                float val2 = stack.pop(); 
                //System.out.println(val2);
                //System.out.println(val1);
                switch(c) 
                { 
                    case '+': 
                    stack.push(val2+val1); 
                    break; 
                      
                    case '-': 
                    stack.push(val2- val1); 
                    break; 
                      
                    case '÷': 
                    stack.push(val2/val1); 
                    break; 
                      
                    case 'x': 
                    stack.push(val2*val1); 
                    break; 
              }
              //System.out.println(stack.peek()); 
            } 
        } 
        return stack.pop();
    }
    public void equal_button(View view)
    {
    	
    	TextView display=(TextView) findViewById(R.id.textView1);
    	CharSequence values=display.getText();
    	//String exp=(String)values;
    	float ans;
    	String val=values.toString();
    	if((val).contains("%"))
    	{
    		String proc=val.substring(0,val.length()-1);
    		ans=Float.parseFloat(proc);
    		ans/=100;
    		
    	}
    	else
    	{
	    	String pos=infpos(values);
	    	ans=evaluatePostfix(pos);
	    	
	    	//display.setText(pos);
    	}
    	display.setText(""+ans);
    }

}