package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.OpLog;
import com.mind.mind_warehouse.vo.LogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OpLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpLog record);

    OpLog selectByPrimaryKey(Integer id);

    List<OpLog> selectAll();

    int updateByPrimaryKey(OpLog record);

    List<LogVo> selectAllPlus(@Param("location") String location,@Param("username") String username,@Param("time") Date time);

    /**
     * 查询操作日志记录
     *
     * @param operId 操作日志记录主键
     * @return 操作日志记录
     */
    public LogVo selectLogByOperId(Integer operId);

    /**
     * 查询操作日志记录列表
     *
     * @param logVo 操作日志记录
     * @return 操作日志记录集合
     */
    public List<LogVo> selectLogList(LogVo logVo);

    /**
     * 新增操作日志记录
     *
     * @param logVo 操作日志记录
     * @return 结果
     */
    public int insertLog(LogVo logVo);

    /**
     * 修改操作日志记录
     *
     * @param logVo 操作日志记录
     * @return 结果
     */
    public int updateLog(LogVo logVo);

    /**
     * 删除操作日志记录
     *
     * @param operId 操作日志记录主键
     * @return 结果
     */
    public int deleteLogByOperId(Integer operId);

    /**
     * 批量删除操作日志记录
     *
     * @param operIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogByOperIds(Integer[] operIds);
}