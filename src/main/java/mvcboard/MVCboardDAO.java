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
		//오라클의 그룹함수는 count()를 사용해서 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board ";
		//매개변수로 전달된 검색어가 있는 경우에만 where절을 동적으로 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			//Statement 인스턴스 생성(정적쿼리문 실행)
			stmt = con.createStatement();
			//커리문을 실행한 후 결과를 ResultSet으로 반환받는다.
			rs = stmt.executeQuery(query);
			/*count()함수는 조건에 상관없이 항상 결과가 인출되므로 if문과 같은 조건절없이 바로 next()함수를 실행할수있다.*/
			rs.next();
			//반환된 결과를 저장한다.
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
		//오라클에서 인출한 레코드를 저장하기위한 List생성
		List<MVCboardDTO> board = new Vector<MVCboardDTO>();
		
		//레코드 인출을 위한 쿼리문 작성
		String query = "SELECT * FROM board ";
		//검색어 입력 여부에 따라서 where절은 조건부로 추가됨
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";	
		}
		//일련번호의 내림차순으로 정렬한 후 인출한다.
		query += " ORDER BY board_id DESC ";
		//게시판은 항상 최근에 작성한 게시물이 상단에 노출되어한다.
		try {
			//prepareStatement 인스턴스 생성
			psmt = con.prepareStatement(query);
			//쿼리문 실행 및 결과반환 (Result set)
			rs = psmt.executeQuery();
			//ResultSet의 크기만큼 즉, 인출된 레코드가 갯수만큼 반복
			
			while(rs.next()) {
				//하나의 레코드를 저장하기 위해 DTO인스턴스 생성
				MVCboardDTO dto = new MVCboardDTO();
				
				/*
				 * ResultSet인스턴스에서 데이터를 추출할때 멤버변수의 타입에 따라 getString(),getInt(),getDate()로 구문하여 호출한다.
				 * 이 데이터를 DTO의 setter를 이용해서 저장한다.
				 * */
				
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
			   
				
				//레코드가 저장된 DTO를 List에 갯수만큼 추가한다.
				board.add(dto);
			}
			
		} 
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		//마지막으로 인출한 레코드를 저장한 List를 반환한다.
		return board;	
		
	}
	
	public List<MVCboardDTO> selectListPage(Map<String, Object> map){
		List<MVCboardDTO> board = new Vector<MVCboardDTO>();
		String query = 
			    " SELECT * FROM ( "
			  + "  SELECT Tb.*, ROWNUM rNum FROM ( "
			  + "    SELECT * FROM Board ";
			  
			// 검색어가 있을 경우 조건을 추가
			if (map.get("searchWord") != null) {
			    query += " WHERE " + map.get("searchField") 
			            + " LIKE '%" + map.get("searchWord") + "%'";
			}

			// 게시글을 작성일 기준으로 내림차순 정렬
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
	
	
}
