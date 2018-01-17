package interview;

/**
 * һ���ַ�����ȡ�ַ�
 * @author dmf
 *
 */
public class SubString {

	public static void main(String[] args) {
		//String str = "��a���л�abc�Ұ�����def';
		String str = "��ABC��";
		
		//��ȡӦ�ý�ȡ��λ��
		int size = trimGBK(str.getBytes(),5);
		System.out.println(str.substring(0,size));
	}

	private static int trimGBK(byte[] bytes, int n) {
		
		int num = 0;
		boolean half = false; //�Ƿ�Ϊ���ֵ�һ��
		for(int i=0;i<n;i++){
			//�ж��Ƿ�Ϊ����
			if (bytes[i]<0 && !half) {  //��ʾΪ����
				half = true;
			}else {
				num++;
				half = false;
			}
		}	
		return num;
	}
}
