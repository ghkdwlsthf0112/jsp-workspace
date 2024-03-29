package board.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// String에 기본적으로 들어있는 해시 메서드
		// 해당 문자열의 값만으로 셜과를 충분히 도출해낼 수 있기 때문에
		// 비밀번호와 해시로 사용하기에는 적합하지 않다
		System.out.println("ABCD".hashCode());
		System.out.println(("abcdddddddddddddddddddddddd"
				+"aaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+"scacdvascdssssssssdcdscs"
				+"scdasddasdasdasdsadasdasdasdasd").hashCode());
		
		// 사용할 수 있는 해시 알고리즘 종류 : SHA256, DHA512, MD5 ...
		
		String message = "hello, word";
		
		try {
			// 해당 알고리즘의 인스턴스 생성
			MessageDigest eater = MessageDigest.getInstance("SHA256");
			
			// 해당 알고리즘에 워하는 메세지 세팅
			eater.update(message.getBytes());
			
			
			byte[] result = eater.digest();
			
			System.out.println(Arrays.toString(eater.digest()));

			// 일반적으로 결과를 16진수 문자열로 바꿔서 DB에 저장하는 편이다
			StringBuilder builder = new StringBuilder();
			
			for (int i = 0; i < result.length; ++i) {
				// System.out.printf("%02x", result[i]);
				builder.append(String.format("%02X", result[i]));
			}
			System.out.println(builder);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
