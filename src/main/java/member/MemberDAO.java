package member;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {

   public MemberDAO() {
      super();
      
      
   }
   
   
 //로그인 기능 구현
   public MemberDTO getUser(String uid, String upass) {
      MemberDTO dto = null;
      String query = "SELECT * FROM users where username = ? and password = ? ";
      
      try {
         psmt = con.prepareStatement(query);
         psmt.setString(1, uid);
         psmt.setString(2, upass);
         rs = psmt.executeQuery();
         
         if(rs.next()) {
            dto = new MemberDTO();
            dto.setUsername(rs.getString("username"));
            dto.setPassword(rs.getString("password"));
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dto;
   }
   
   @Override
   public void close() {
      super.close();
   }
   
   //회원가입 기능 구현
   public int regiUser(MemberDTO dto) {
	   int result = 0;
	   
	   try {
		   String query = 
				   "INSERT INTO users (last_name, first_name, username, password, email) VALUES (?,?,?,?,?)";
	   
		   psmt = con.prepareStatement(query);
		   psmt.setString(1, dto.getLast_Name());
		   psmt.setString(2, dto.getFirst_Name());
		   psmt.setString(3, dto.getUsername());
		   psmt.setString(4, dto.getPassword());
		   psmt.setString(5, dto.getEmail());
		   
		   result = psmt.executeUpdate();
		
	   }
	   catch (Exception e) {
		   System.out.println("INSERT 중 예외 발생");
	        e.printStackTrace();
	}
	   return result;
   }
   
   //아이디 중복 검사 기능
   public boolean isUsernameDuplicate(String username) {
	    String query = "SELECT COUNT(*) FROM users WHERE username = ?";
	    
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, username);  

	        try (ResultSet rs = psmt.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt(1);  
	                return count > 0;  
	            }
	        }
	    } catch (Exception e) {
	    	System.out.println("아이디 검사중 예외 발생");
	        e.printStackTrace();
	    }
	    return false;  
	    
	}
   //이메일 중복검사 기능
   public boolean isEmailDuplicate(String email) {
	    String query = "SELECT COUNT(*) FROM users WHERE email = ?";
	    
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, email);  

	        try (ResultSet rs = psmt.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt(1);  
	                return count > 0;  
	            }
	        }
	    } catch (Exception e) {
	    	System.out.println("이메일 검사중 예외 발생");
	        e.printStackTrace();
	    }
	    return false;  
	    //count > 0이면 중복된 이메일이 있다는 뜻으로 true를 반환합니다. 
	    //그렇지 않으면 false를 반환합니다.
	}
     
   public int getUserIdByUserName(String username) {
	   
	   String query = "SELECT user_id FROM users WHERE username = ?";
	   int userId = -1;
	   try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, username);
	        
	         rs = psmt.executeQuery();
	         
	         if(rs.next()) {
	            userId = rs.getInt("user_id");
	         }
	         
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   
	   	
	      return userId;
   }
   
   public String getUserType(String username) {
	   String query = "SELECT user_type FROM users WHERE username = ?";
	   String user_type = "";
	   try {
		   
		   psmt = con.prepareStatement(query);
	       psmt.setString(1, username);
	       
	       rs = psmt.executeQuery();
	         
	       if(rs.next()) {
	    	   user_type = rs.getString("user_type");
	       }
	         
		
	} catch (Exception e) {
		 e.printStackTrace();
	}
	
	 return user_type;
}
   
   
   

}
