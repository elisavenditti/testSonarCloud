package application;

import java.util.ArrayList;

public class Expression {
	private String espressione;
	private static ArrayList<String> numbers = new ArrayList<>();
	private static ArrayList<String> parentesi = new ArrayList<>();
	private static ArrayList<String> operazioni = new ArrayList<>();
	
	public Expression(String e) {
		this.espressione = e;
		int i=0;
		for(i=0; i<10; i++) {
			numbers.add(Integer.toString(i));
		}
		numbers.add(".");
		parentesi.add("(");
		parentesi.add(")");
		operazioni.add("/");
		operazioni.add("*");
		operazioni.add("+");
		operazioni.add("-");
	}
	
	private boolean checkCorrectness(ArrayList<String> array) {
		
		int i=0;
		boolean correct = true;
		
		//in posizione pari deve esserci un numero e in posizione dispari un'operazione
		
		
		for(i=0; i<array.size(); i++) {
			
			if(i%2==0 || i==array.size()-1) {
				if(array.get(i).compareTo("/")==0||array.get(i).compareTo("*")==0 ||
						array.get(i).compareTo("+")==0 || array.get(i).compareTo("-")==0) correct=false;
			} else {
				if(array.get(i).compareTo("/")!=0 && array.get(i).compareTo("*")!=0 &&
						array.get(i).compareTo("+")!=0 && array.get(i).compareTo("-")!=0) correct=false;
			}
			
		}
		
		return correct;	
	}
	
	
	
	
	private ArrayList<String> expressionToArray() {
		ArrayList<String> res = new ArrayList<>();
		int i=1;	
		String temp = this.espressione;
		while(true) {
			
			if(temp.length()==0) break;
			else if(temp.substring(0, 1).compareTo("+")==0 || temp.substring(0, 1).compareTo("-")==0 ||
					temp.substring(0, 1).compareTo("*")==0 || temp.substring(0, 1).compareTo("/")==0) {
				res.add(temp.substring(0,1));
				temp = temp.substring(1);	
			}else{
				String num = temp.substring(0, 1);
				int len = temp.length();
				if(len>1) {
					while(numbers.contains(temp.substring(i, i+1))) {
						num = num + temp.substring(i, i+1);
						i++;
						len--;
						if(len==1) break;
					}
				}
				res.add(num);
				temp = temp.substring(i);
				i=1;
			}
			
		}
		
		return res;
	}
	
	private int numberOfOccurrences(ArrayList<String> expr, String s) {
		int i=0;
		
		for(String element: expr) {
			if(element.compareTo(s)==0) i++;
		}		
		
		return i;
	}
	
	
	private ArrayList<String> calculate(ArrayList<String> expr, String op) {
		
		
		int i=0;	
		for(i=0; i<expr.size(); i++) {
			if(expr.get(i).compareTo(op)==0) {
				double prev = Double.parseDouble(expr.get(i-1));
				double next = Double.parseDouble(expr.get(i+1));
				double res=0;
				switch(op) {
					case "+":
						res = prev+next;
						break;
					case "-":
						res = prev-next;
						break;
					case "*":
						res = prev*next;
						break;
					case "/":
						res = prev/next;
						break;
					default: break;
				}
				expr.set(i-1, Double.toString(res));
				expr.remove(i+1);
				expr.remove(i);

				return expr;
			}
		}

		return expr;
	}
		
	
	public String calculateResult() {
		
		ArrayList<String> a = expressionToArray();
		
		if(!checkCorrectness(a)) {
			return "error";
		}
		
		//FASE DELLA DIVISIONE
		int numDiv = this.numberOfOccurrences(a, "/");
		int k;
		for(k=0; k<numDiv; k++) {
			calculate(a, "/");
		}
		
		//FASE DELLA MOLTIPLICAZIONE
		int numMolt = this.numberOfOccurrences(a, "*");
		int j;
		for(j=0; j<numMolt; j++) {
			calculate(a, "*");
		}
		
		//FASE DELLA SOTTRAZIONE
				int numSott = this.numberOfOccurrences(a, "-");
				int t;
				for(t=0; t<numSott; t++) {
					calculate(a, "-");
				}
		
		//FASE DELL'ADDIZIONE
		int numAdd = this.numberOfOccurrences(a, "+");
		int z;
		for(z=0; z<numAdd; z++) {
			calculate(a, "+");
		}
		
		
		
		if(a.size()!=1) return "error";
		return a.get(0);
	}
}
