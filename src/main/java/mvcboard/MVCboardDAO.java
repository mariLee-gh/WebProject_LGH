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
	
		String query = "SELECT COUNT(*) FROM board ";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
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
	
	//게시판 목록에 출력할 레코드를 인출하기 위한 메서드 정의
	public List<MVCboardDTO> selectList	(Map<String, Object>map) {
		
		List<MVCboardDTO> board = new Vector<MVCboardDTO>();
		
		String query = "SELECT * FROM board ";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";	
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
			  + "    SELECT * FROM Board ";
			  
		
			if (map.get("searchWord") != null) {
			    query += " WHERE " + map.get("searchField") 
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
	//글쓰기 처리를 위한 쿼리문 실행
	//만약 파일들을 위한 테이블이 따로있다면 조인문을 여기서 써야하는가? 
	public int insertWrite(MVCboardDTO dto) {
		int result = 0;
		try {

			String query =
					"INSERT INTO board ( "
							+ "  user_id, title, content, ofile, sfile)"
							+ " VALUES ( "
							+ " ?,?,?,?,?)";
		
				psmt = con.prepareStatement(query);
				//인스턴스를 통해 인파라미터 설정
				psmt.setString(1, dto.getUser_id());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				//쿼리문 실행. insert쿼리의 경우 입력된 행의 갯수가 반환됨.
				result = psmt.executeUpdate();
				
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
