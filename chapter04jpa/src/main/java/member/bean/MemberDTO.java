package member.bean;

import lombok.Data;
import member.entity.MemberEntity;

@Data
public class MemberDTO {
    private int seq;
    private String id;
    private String name;
    private String pwd;

    // public MemberEntity toEntity(MemberDTO dto) {
    //     MemberEntity entity = new MemberEntity();
    //     entity.setSeq(dto.getSeq());
    //     entity.setId(dto.getId());
    //     entity.setName(dto.getName());
    //     entity.setPwd(dto.getPwd());

    //     return entity;
    // }

    public MemberEntity toEntity() {
        MemberEntity entity = new MemberEntity();
        entity.setSeq(seq);
        entity.setId(id);
        entity.setName(name);
        entity.setPwd(pwd);
        
        return entity;
    }
}
