package org.stevedowning.commons.idutils.datastructures;

import junit.framework.TestCase;

import org.stevedowning.commons.idutils.Id;
import org.stevedowning.commons.idutils.IdMaps;
import org.stevedowning.commons.idutils.TestUser;
import org.stevedowning.commons.idutils.idfactory.LongIdFactory;
import org.stevedowning.commons.idutils.ids.LongId;

public class AbstractIdMapTest extends TestCase {
    private static enum SpecialUserId implements Id<TestUser> {
        ANONYMOUS_USER_ID, ADMIN_USER_ID;
    }
    
    public void testBasic() {
        LongIdFactory idFactory = new LongIdFactory();
        IdMap<TestUser> userMap = IdMaps.newIdHashMap();
        LongId<TestUser> steveId = idFactory.generateId();
        TestUser steve = new TestUser("Steve", steveId);
        TestUser kate = new TestUser("Kate", idFactory.<TestUser>generateId());
        
        userMap.put(steve);
        userMap.put(kate);
        
        assertTrue(userMap.containsKey(steveId));
        assertEquals(steve, userMap.get(steveId));
        assertEquals(2, userMap.size());
    }
    
    public void testEnumId() {
        TestUser anon = new TestUser("Anonymous", SpecialUserId.ANONYMOUS_USER_ID);
        TestUser admin = new TestUser("Admin", SpecialUserId.ADMIN_USER_ID);
        
        IdMap<TestUser> userMap = IdMaps.newIdHashMap();
        userMap.put(anon);
        userMap.put(admin);

        assertTrue(userMap.containsKey(SpecialUserId.ANONYMOUS_USER_ID));
        assertEquals(anon, userMap.get(SpecialUserId.ANONYMOUS_USER_ID));
        assertEquals(2, userMap.size());
    }
}
