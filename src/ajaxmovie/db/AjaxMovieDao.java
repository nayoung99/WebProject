package ajaxmovie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import memo.db.MemoDto;
import oracle.db.DbConnect;

public class AjaxMovieDao {
	DbConnect db=new DbConnect();
	
	//출력
		public List<AjaxMovieDto> getAllDatas()
		{
			List<AjaxMovieDto> list=new Vector<AjaxMovieDto>();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select * from ajaxmovie order by num desc";
			conn=db.getConnection();
			
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					AjaxMovieDto dto=new AjaxMovieDto();
					dto.setNum(rs.getString("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setImage(rs.getString("image"));
					dto.setRate(rs.getString("rate"));
					dto.setLikes(rs.getInt("likes"));
					dto.setWriteday(rs.getTimestamp("writeday"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				db.dbClose(rs, pstmt, conn);
			}
			
			return list;
		}

		//부분 출력시 필요한 메서드
				public List<AjaxMovieDto> getPagingDatas(int start)
				{
					List<AjaxMovieDto> list=new Vector<AjaxMovieDto>();
					Connection conn=null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					String sql="select a.  * from (select ROWNUM as RNUM, b.* from "
							+ "(select * from ajaxmovie order by num desc)b)a "
							+ "where a.RNUM>=? and a.RNUM<=?";
					conn=db.getConnection();
					
					try {
						pstmt=conn.prepareStatement(sql);
						//바인딩
						pstmt.setInt(1, start);
						pstmt.setInt(2, start+9);
						rs=pstmt.executeQuery();
						while(rs.next())
						{
							AjaxMovieDto dto=new AjaxMovieDto();
							dto.setNum(rs.getString("num"));
							dto.setWriter(rs.getString("writer"));
							dto.setSubject(rs.getString("subject"));
							dto.setContent(rs.getString("content"));
							dto.setImage(rs.getString("image"));
							dto.setRate(rs.getString("rate"));
							dto.setLikes(rs.getInt("likes"));
							dto.setWriteday(rs.getTimestamp("writeday"));
							
							list.add(dto);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						db.dbClose(rs, pstmt, conn);
					}
					
					return list;
				}
				
				//전체 갯수 구하는 메서드
				public int getTotalCount() {
					Connection conn=null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					
					conn=db.getConnection();
					String sql="select count(*) from ajaxmovie";
					
					int total=0;
					
					try {
						pstmt=conn.prepareStatement(sql);
						rs=pstmt.executeQuery();
						if(rs.next())
							total=rs.getInt(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						db.dbClose(rs,pstmt, conn);
					}
					return total;
				}
		//추가
		public void insertMovie(AjaxMovieDto dto)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
				
			String sql="insert into ajaxmovie (NUM, WRITER, PASS, SUBJECT,CONTENT,IMAGE,RATE,LIKES,WRITEDAY)"
			         +"values (seq_movie.nextval,"
					+ "?,?,?,?,?,?,0,sysdate)";
			conn=db.getConnection();
			try {
				pstmt=conn.prepareStatement(sql);
				//바인딩
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getPass());
				pstmt.setString(3, dto.getSubject());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getImage());
				pstmt.setString(6, dto.getRate());
				
				
				//실행
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				db.dbClose(pstmt, conn);
			}
		}
		
		//num 번째 dto 반환 //list출력부분 복붙해서 수정
		public AjaxMovieDto getData(String num)
		{
			AjaxMovieDto dto=new AjaxMovieDto();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select * from ajaxmovie where num=?";
			conn=db.getConnection();
			
			try {
				pstmt=conn.prepareStatement(sql);
				//바인딩
				pstmt.setString(1, num);
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					dto.setNum(rs.getString("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setRate(rs.getString("rate"));
					dto.setImage(rs.getString("image"));
					dto.setLikes(rs.getInt("likes"));
					dto.setWriteday(rs.getTimestamp("writeday"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				db.dbClose(rs,pstmt, conn);
			}
			return dto;
		}
		//추천수 증가
			public int updateLikes(String num) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = "update ajaxmovie set likes=likes+1 where num=?";
				int n=0;
				conn = db.getConnection();
				try {
					pstmt = conn.prepareStatement(sql);
					//바인딩
					pstmt.setString(1,num);
					//실행
					pstmt.execute();
					
					//증가된 추천수 다시 얻기
					n=this.getData(num).getLikes();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					db.dbClose(pstmt, conn);
				}
				return n;
			}
			
			//비번 체크하는 메서드
			public boolean isPassCheck(String num,String pass) {
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				
				conn=db.getConnection();
				String sql="select count(*) from ajaxmovie where num=? and pass=?";
				
				int r=0;
				
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,num);
					pstmt.setString(2,pass);
					rs=pstmt.executeQuery();
					if(rs.next())
						r=rs.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					db.dbClose(rs,pstmt,conn);
				}
				return r==1?true:false;
			}
			
			//삭제
					public void deleteBoard(String num) {
						Connection conn=null;
						PreparedStatement pstmt=null;
				
						
						conn=db.getConnection();
						String sql="delete from ajaxmovie where num=?";
						
						int r=0;
						try {
							pstmt=conn.prepareStatement(sql);
							pstmt.setString(1,num);
							
							pstmt.execute();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							db.dbClose(pstmt,conn);
						}
				
					}
					
					//수정
					public void updateMovie(AjaxMovieDto dto) 
					{
						Connection conn=null;
						PreparedStatement pstmt=null;
						String sql="update ajaxmovie set writer=?,subject=?,content=?,rate=?,image=?,likes=? where num=?";
						
						conn=db.getConnection();
						try {
							pstmt=conn.prepareStatement(sql);
						
							pstmt.setString(1, dto.getWriter());
							pstmt.setString(2, dto.getSubject());
							pstmt.setString(3, dto.getContent());
							pstmt.setString(4, dto.getRate());
							pstmt.setString(5, dto.getImage());
							pstmt.setInt(6, dto.getLikes());
							pstmt.setString(7, dto.getNum());
							//실행
							pstmt.execute();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							db.dbClose(pstmt, conn);
						}
					}
					
					
				}	
					
					
					
					
					
					
					
					
					


