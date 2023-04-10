package com.tju.elmcloud.mapper;

import com.tju.elmcloud.po.CreditEntity;
import com.tju.elmcloud.po.CreditVO;
import com.tju.elmcloud.po.ValidCredit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditMapper {

    public List<ValidCredit> getValidCredit(String userId);

    public List<CreditVO> getCreditTotalDetails(String userId);

    public List<CreditVO> getCreditOfAdd(String userId);

    public List<CreditVO> getCreditOfSpend(String userId);

    public List<CreditVO> getCreditOfOutTime(String userId);

    public int saveCreditDetail(CreditEntity creditEntity);
}
