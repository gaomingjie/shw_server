package top.itning.server.shwgroup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import top.itning.server.shwgroup.entity.Group;

import java.util.UUID;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceTest {
    @Autowired
    private GroupService groupService;

    @Test
    public void testCreateGroup() {
        Mono<Group> group = groupService.createGroup("测试创建群组1", "测试教师名1", "0001");
        Group block = group.block();
        assertNotNull(block);
        assertEquals(block.getGroupName(), "测试创建群组1");
        assertEquals(block.getTeacherName(), "测试教师名1");
        assertEquals(block.getTeacherNumber(), "0001");
    }

    @Test
    public void testDeleteGroup() {
        boolean no = true;
        try {
            groupService.deleteGroup("0001", "bc960acc2dc9423ea40f66ec1d21f5dc").block();
            groupService.deleteGroup("0002", UUID.randomUUID().toString()).block();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            no = false;
        }
        assertFalse(no);
    }

    @Test
    public void testUpGroupName() {
        groupService.updateGroupName("setTeacherNumber", "a8d328832-3e1c-4423-810d-4e1f789d5548", "新群组名").block();
    }

    @Test
    public void testIsHaveAnyGroup() {
        assertTrue(groupService.isHaveAnyGroup("setTeacherNumber").block());
        assertFalse(groupService.isHaveAnyGroup(UUID.randomUUID().toString()).block());
    }
}