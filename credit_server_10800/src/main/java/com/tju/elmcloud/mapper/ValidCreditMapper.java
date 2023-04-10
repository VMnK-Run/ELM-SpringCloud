package com.tju.elmcloud.mapper;

import com.tju.elmcloud.po.ValidCredit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ValidCreditMapper {

    public int saveValidCredit(ValidCredit validCredit);

    public List<ValidCredit> getAllValidCredit(String userId);

    @Delete("delete from validCredit where vcId=#{vcId}")
    public int removeValidCreditById(int vcId);

    public List<ValidCredit> getOutTimeCredits(String userId);
}
