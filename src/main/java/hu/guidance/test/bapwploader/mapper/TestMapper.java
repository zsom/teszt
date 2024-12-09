package hu.guidance.test.bapwploader.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import hu.guidance.test.bapwploader.model.User;

public interface TestMapper {
	@Select("SELECT t_id, t_name FROM zsom_test ORDER BY t_id")
	@Results({
        @Result(column="t_id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="t_name", property="name", jdbcType=JdbcType.VARCHAR)
    })
	List<User> selectAll();
	
	
	@Select("SELECT t_id, t_name FROM zsom_test WHERE t_id = #{id}")
	@Results({
        @Result(column="t_id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="t_name", property="name", jdbcType=JdbcType.VARCHAR)
    })
	User get(int id);
	
	
	@Insert({
        "insert into zsom_test (t_name)",
        "values (#{name})"
    })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(User user);
	
	@Insert({
        "insert into zsom_test (t_name, descr)",
        "values (#{name}, #{description})"
    })
	//@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert2(@Param("name") String name, @Param("description") String description);
}
