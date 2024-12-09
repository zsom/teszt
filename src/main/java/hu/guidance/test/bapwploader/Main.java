package hu.guidance.test.bapwploader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hu.guidance.test.bapwploader.mapper.TestMapper;
import hu.guidance.test.bapwploader.mapper.UserMapper;
import hu.guidance.test.bapwploader.mapper.WPLoaderMapper;
import hu.guidance.test.bapwploader.model.Hierarchy;
import hu.guidance.test.bapwploader.model.User;
import hu.guidance.test.bapwploader.model.Workpackage;

public class Main {
	private static SqlSessionFactory sqlSessionFactory;
	
	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			init();
			
			loadWP();
			
			//test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}

	private static void loadWP() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			WPLoaderMapper wpLoader = session.getMapper(WPLoaderMapper.class);
		
			Random random = new Random();
			
			
			int parentId = 5238;
			Workpackage wp = new Workpackage(5, "gépi 3");  //5 = Zsom
			wp.setEv("2024");
			wp.setSzlaNetto(String.valueOf(random.nextInt(10000)));
			
			
			wpLoader.insertWP(wp);
			
			session.commit();
			
			int wpId = wp.getId();
			
			System.out.println("inserted wpId:" + wpId);
			
			
			wpLoader.insertHierarchy(new Hierarchy(wpId, wpId, 0));
			wpLoader.insertHierarchy(new Hierarchy(parentId, wpId, 1));
			
			List<Hierarchy> parentHierarchyList = wpLoader.listParentHierarchy(parentId);
			for (Hierarchy hierarchy : parentHierarchyList) {
				if(hierarchy.getHierarchy() != 0) {
					System.out.println(hierarchy);
					wpLoader.insertHierarchy(new Hierarchy(hierarchy.getFrom(), wpId, hierarchy.getHierarchy() + 1));
				}
			}
			
			session.commit();
			
			
			wpLoader.insertCustomValue(wpId, Workpackage.CF_BER_KAT, wp.getBerKat());
			wpLoader.insertCustomValue(wpId, Workpackage.CF_EV, wp.getEv());
			//wpLoader.insertCustomValue(wpId, Workpackage.CF_KTSG_HELY2, null);
			wpLoader.insertCustomValue(wpId, Workpackage.CF_SZLA_ARF, wp.getArfolyam());
			wpLoader.insertCustomValue(wpId, Workpackage.CF_SZLA_DEV, wp.getSzlaDev());
			wpLoader.insertCustomValue(wpId, Workpackage.CF_SZLA_NETTO, wp.getSzlaNetto());
			
			session.commit();
		}
	}

	private static void test1() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			List<User> users = userMapper.selectAll();
			
			System.out.println("users: " + users);
			
			
			User user = userMapper.get(5);
		
			System.out.println("User: " + user);
			
			TestMapper testMapper = session.getMapper(TestMapper.class);
			
			System.out.println("test: " + testMapper.selectAll());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
			
			//User u = new User(sdf.format(new Date()));
			//int id = testMapper.insert(u);
			
			testMapper.insert2(sdf.format(new Date()), "deszkr");
			
			session.commit();
			
			//System.out.println("id: " + id + ", new user: " + u);
		}
		
		
	}

	private static void init() throws FileNotFoundException {
		InputStream inputStream = new FileInputStream("d:\\munka\\guidance\\boardapp\\wp_tolto\\mybatis_config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSessionFactory.getConfiguration().addMappers("hu.guidance.test.bapwploader.mapper");
		
	}
}
