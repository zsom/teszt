package hu.guidance.test.bapwploader.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import hu.guidance.test.bapwploader.model.Hierarchy;
import hu.guidance.test.bapwploader.model.Workpackage;

public interface WPLoaderMapper {

	@Select("SELECT * FROM relations WHERE to_id = #{parentId}")
	@Results({
        @Result(column="id", property="id", id=true),
        @Result(column="from_id", property="from"),
        @Result(column="to_id", property="to"),
        @Result(column="hierarchy", property="hierarchy")
    })
	List<Hierarchy> listParentHierarchy(int parentId);
	
	@Insert({
        "INSERT INTO work_packages (project_id, type_id, status_id, author_id, priority_id, created_at, updated_at, subject) ",
        "VALUES (#{projectId}, #{typeId}, #{statusId}, #{authorId}, #{priorityId}, #{created}, #{created}, #{subject})"
    })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertWP(Workpackage wp);
	
	@Insert({
        "INSERT INTO relations (from_id, to_id, HIERARCHY, count) ",
        "VALUES (#{from}, #{to}, #{hierarchy}, #{count})"
    })
	int insertHierarchy(Hierarchy hierarchy);
	
	
	@Insert({
        "INSERT INTO custom_values (customized_type, customized_id, custom_field_id, value) ",
        "VALUES('WorkPackage', #{wpId}, #{cfId}, #{value})"
    })
	int insertCustomValue(@Param("wpId") int workpackageId, @Param("cfId") int customFieldId, @Param("value") String value);
}
