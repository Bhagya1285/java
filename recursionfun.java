public class recursionfun
{
    public String fun(int n)
    {
        if(n==11) 
        {return "Thabi";
        }

        String s=fun(n+1);
        System.out.println(n);
        return s;
    }
	public static void main(String[] args) {
		Main m=new Main();
		System.out.println(m.fun(1));
	}
}
