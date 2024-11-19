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
   
   
   
   

}
