package member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import member.bean.MemberDTO;

@Data
@Entity
@Table(name = "membertbl")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "id", nullable = false, unique = true, length = 30)
    private String id;
    
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "pwd", nullable = false , length = 300)
    private String pwd;

    public MemberDTO toDto() {
        MemberDTO dto = new MemberDTO();
        dto.setSeq(seq);
        dto.setId(id);
        dto.setName(name);
        dto.setPwd(pwd);
        
        return dto;
    }
}
