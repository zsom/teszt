package hu.guidance.test.bapwploader.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import hu.guidance.test.bapwploader.model.User;

public interface UserMapper {

	@Select("SELECT id, login FROM users ORDER BY id")
	@Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="login", property="name", jdbcType=JdbcType.VARCHAR)
    })
	List<User> selectAll();
	
	
	@Select("SELECT id, login FROM users WHERE id = #{id}")
	@Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="login", property="name", jdbcType=JdbcType.VARCHAR)
    })
	User get(int id);
}
