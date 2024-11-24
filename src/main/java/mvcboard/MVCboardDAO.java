package mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

import mvcboard.MVCboardDTO;



public class MVCboardDAO extends DBConnPool{
	
	public MVCboardDAO() {
		super();
	}
	//게시물의 갯수를 카운트
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
	
		String query = "SELECT COUNT(*) FROM board WHERE category = '"+ map.get("category")+ "'";	
		
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();

			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	

	public List<MVCboardDTO> selectList	(Map<String, Object>map) {
		
		List<MVCboardDTO> board = new Vector<MVCboardDTO>();
		
		String query = "SELECT * FROM board WHERE category = '"+ map.get("category")+ "'";		
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";	
		}
		
		query += " ORDER BY board_id DESC ";
		
		try {
	
			psmt = con.prepareStatement(query);
			
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				
				MVCboardDTO dto = new MVCboardDTO();
				
				
				
				dto.setBoard_id(rs.getString(1));
				dto.setUser_id(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setDowncount(rs.getInt(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setPostdate(rs.getDate(7));
				dto.setFile_id(rs.getString(8));
				dto.setLike_count(rs.getInt(9));
				dto.setIs_secret(rs.getInt(10));
				dto.setCategory(rs.getString(11));
				dto.setReply_id(rs.getString(12));
			   
				
				
				board.add(dto);
			}
			
		} 
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board;	
		
	}
	
	public List<MVCboardDTO> selectListPage(Map<String, Object> map){
		List<MVCboardDTO> board = new Vector<MVCboardDTO>();
		String query = 
			    " SELECT * FROM ( "
			  + "  SELECT Tb.*, ROWNUM rNum FROM ( "
			  + "    SELECT * FROM Board WHERE category = '"+ map.get("category")+ "'";		
			  
		
			if (map.get("searchWord") != null) {
			    query += " AND " + map.get("searchField") 
			            + " LIKE '%" + map.get("searchWord") + "%'";
			}

	
			query += " ORDER BY postdate DESC "
			      + "   ) Tb "
			      + " ) "
			      + " WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCboardDTO dto = new MVCboardDTO();
				
				dto.setBoard_id(rs.getString(1));
				dto.setUser_id(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setDowncount(rs.getInt(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setPostdate(rs.getDate(7));
				dto.setFile_id(rs.getString(8));
				dto.setLike_count(rs.getInt(9));
				dto.setIs_secret(rs.getInt(10));
				dto.setCategory(rs.getString(11));
				dto.setReply_id(rs.getString(12));
				
				board.add(dto);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
				
	}

	public int insertWrite(MVCboardDTO dto) {
		int result = 0;
		try {

			String query =
					"INSERT INTO board ( "
							+ "  user_id, title, content, ofile, sfile,category)"
							+ " VALUES ( "
							+ " ?,?,?,?,?,?)";
		
				psmt = con.prepareStatement(query);
				//인스턴스를 통해 인파라미터 설정
				psmt.setString(1, dto.getUser_id());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getCategory());
				//쿼리문 실행. insert쿼리의 경우 입력된 행의 갯수가 반환됨.
				result = psmt.executeUpdate();
				
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
/*********************************************************************************/
	//게시물 열람
	 	public MVCboardDTO selectView(String board_id) {
	
	 		MVCboardDTO dto = new MVCboardDTO();
	 		
			String query = "SELECT Bo.*, Me.username FROM board Bo "
					+  " INNER JOIN users Me ON Bo.user_id=Me.user_id"
					+  " WHERE board_id=?"; 
	 		try {
	 			psmt = con.prepareStatement(query); 
	 			psmt.setString(1, board_id); 
	 			rs = psmt.executeQuery(); 
	 			
	 		
	 			if (rs.next()) { 
	 				//결과를 DTO 객체에 저장(member 테이블의 name까지 저장한다.)
	 				dto.setBoard_id(rs.getString(1));
	 				dto.setUser_id(rs.getString(2));
	 				dto.setTitle(rs.getString("title"));
	 				dto.setContent(rs.getString(4));
	 				dto.setPostdate(rs.getDate(7));
	 				dto.setVisitcount(rs.getInt(6));
	 				dto.setUsername(rs.getString("username"));
	 				//파일추가...
	 				dto.setOfile(rs.getString("ofile"));
	 				dto.setSfile(rs.getString("sfile"));
	 			}
	 		}
	 		catch (Exception e) {
	 			System.out.println("게시물 상세보기 중 예외 발생");
	 			e.printStackTrace();
	 		}
	 		System.out.println("dao>"+dto.getUsername());
	 		return dto; //결과 반환
	 	}
	 	

		public void updateVisitCount(String idx) {
		
			String query = "UPDATE board SET "
						 + " visitcount=visitcount+1 "
						 + " WHERE board_id =?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, idx);
				
				//psmt.executeQuery();
				int result = psmt.executeUpdate();
			}
			catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();	 
			}
		}
		
		
		public void downCountPlus(String board_id) {
			String sql = "UPDATE board SET "
					+ " downcount=downcount+1 "
					+ " WHERE board_id=? ";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, board_id);
				psmt.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public int deletePost(String board_id) {
			int result = 0;
			try {
				String query = "DELETE FROM board WHERE board_id=?";
						psmt = con.prepareStatement(query);
						psmt.setString(1, board_id);
						result = psmt.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("게시물 삭제 중 예외 발생");
				e.printStackTrace();
			}
			return result;
		}
		
	
		public int updatePost(MVCboardDTO dto) {
			int result = 0;
			try {
		
				String query = "UPDATE board"
					 + " SET title=?, content=?, ofile=?, sfile=? "
					 + " WHERE board_id=? and user_id=?";
				
	
				psmt = con.prepareStatement(query);
				psmt.setString(1,  dto.getTitle());
				psmt.setString(2,  dto.getContent());
				psmt.setString(3,  dto.getOfile());
				psmt.setString(4,  dto.getSfile());
				psmt.setString(5,  dto.getBoard_id());
				psmt.setString(6,  dto.getUser_id());
		
				result = psmt.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("게시물 수정 중 예외 발생");
				e.printStackTrace();
			}
			return result;
		}
	
	
}
