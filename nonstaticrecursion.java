public class nonstaticrecursion
{
    public void fun(int n)
    {
        if(n==11) return;
        fun(n+1);
        System.out.println(n);
    }
	public static void main(String[] args) {
		Main m=new Main();
		m.fun(1);
	}
}