class Solutionlinkedlist{
public ListNode middleNode(ListNode head) {
        int count=0;
        ListNode curr=head;
        while(curr!=null)
        {
            count++;
            curr=curr.next;
        }
        int half=count/2;
        int i=0;
        ListNode c=head;
        while(i!=half)
        {
            c=c.next;
            i++;
        }
        return c;
        }
    }
