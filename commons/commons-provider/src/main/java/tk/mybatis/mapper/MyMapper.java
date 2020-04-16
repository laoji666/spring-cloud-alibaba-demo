package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:05
*/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
