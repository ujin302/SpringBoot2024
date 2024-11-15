package member.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    public MemberEntity findById(String id);

    // [ 쿼리 메소드 ]
    public List<MemberEntity> findByNameContaining(String value);

    public List<MemberEntity> findByIdContaining(String value);
    
    // [ @Query 어노테이션 ]
    // 검색 대상: 영속성 컨텍스트에 등록된 Entity 객체 (테이블명 사용 X)
    // ?1는 첫번째 매개변수 의미
    @Query("select entity from MemberEntity entity where name like concat('%', ?1, '%')")
    public List<MemberEntity> getSearchName(@Param("value") String value);

    @Query("select entity from MemberEntity entity where id like concat('%', :value, '%')")
    public List<MemberEntity> getSearchId(@Param("value") String value);

    // 컬럼명은 문자열이 와야 하는 자리임. 따라서 변수를 사용할 수 없다.
    // @Query("select entity from MemberEntity entity where ?1 like concat('%', ?2, '%')")
    // public List<MemberEntity> getSearch(String colName, String value);

    @Query("select entity from MemberEntity entity where name like concat('%', ?1, '%')")
    public List<MemberEntity> getSearchName(@Param("value") String value, Pageable pageable);

    @Query("select entity from MemberEntity entity where id like concat('%', :value, '%')")
    public List<MemberEntity> getSearchId(@Param("value") String value, Pageable pageable);

}
