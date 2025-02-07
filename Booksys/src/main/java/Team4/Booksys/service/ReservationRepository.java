package Team4.Booksys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Team4.Booksys.VO.ReservationVO;

public interface ReservationRepository extends JpaRepository<ReservationVO, Long> {
	@Query("SELECT COUNT(*) FROM RESERVATION WHERE start_time = ?1 AND tid = ?2 AND isdeleted = 0")
	public int numberOfReservationBytimeAndtid(String start_time, int tid);
	
	@Query("SELECT COUNT(*) FROM RESERVATION WHERE tid = ?1")
	public int numberOfReservationByTableId(int uid);

	@Query("SELECT COUNT(*) FROM RESERVATION WHERE start_time between ?1 and ?2")
	public int numberOfReservationByTime(String t1, String t2);
	
	
	@Query("SELECT a FROM RESERVATION a WHERE a.start_time = ?1 AND a.tid = ?2 AND a.rank > ?3 ORDER BY a.rank asc")//확인필요
	public List<ReservationVO> findAllReservationBytimeAndtidAndRank(String start_time, int tid, int rank);
	
	@Query("SELECT a FROM RESERVATION a WHERE a.uid = ?1 AND a.isdeleted = 0 ORDER BY a.start_time asc" )
	public List<ReservationVO> findAllByuidForUser(int tid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET rank = rank - 1 WHERE oid = ?1" )
	public void updateReservationRank(int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET wait = 0 WHERE rank = 0" )
	public void updateReservationWait(int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET isdeleted = 1 WHERE oid = ?1" )
	public void updateReservationIsdeleted(int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET people_number = ?1 WHERE oid = ?2" )
	public void updateReservationPeopleNumber(int people_num, int oid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RESERVATION SET people_number = ?1, start_time = ?2, tid = ?3, wait = ?4, rank = ?5  WHERE oid = ?6" )
	public void updateReservationValues(int people_num, String start_time, int tid, int wait, int rank ,int oid);
	
	
	public List<ReservationVO> findAllByuid(int uid);
	
	public ReservationVO findByOid(int oid);
	
	
	@Transactional 
	@Modifying
	@Query("DELETE FROM RESERVATION WHERE oid=?1")
	public void deleteReservationbyoid(int oid);

	@Query("SELECT COUNT(*) FROM RESERVATION WHERE uid=?1")
	public int countReservationByUser(int oid);
}
