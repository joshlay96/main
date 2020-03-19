package seedu.address.model.listmanagers.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.uniquelist.UniqueList;
import seedu.address.model.util.uniquelist.exceptions.DuplicateElementException;
import seedu.address.model.util.uniquelist.exceptions.ElementNotFoundException;

class UniqueActivityListTest {

    public static final Activity FIXED_ACTIVITY_1 =
            new Activity(new Title("Hot Spring"),
                    new Priority(1),
                    new Duration(3),
                    new Location("Hokkaido"),
                    new HashSet<>());

    public static final Activity FIXED_ACTIVITY_2 =
            new Activity(new Title("See Deers"),
                    new Priority(1),
                    new Duration(1),
                    new Location("Nara"),
                    new HashSet<>());

    private static final Activity FIXED_ACTIVITY_3 =
            new Activity(new Title("Eat Mochi"),
                    new Priority(2),
                    new Duration(1),
                    new Location("Dazaifu"),
                    new HashSet<>());

    private final UniqueList<Activity> uniqueActivityList = new UniqueList<>();

    @Test
    public void containsNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void containsActivityListNotInListReturnsFalse() {
        assertFalse(uniqueActivityList.contains(FIXED_ACTIVITY_1));
    }

    @Test
    public void containsActivityListInListReturnsTrue() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertTrue(uniqueActivityList.contains(FIXED_ACTIVITY_1));
    }

    @Test
    public void containsActivityListWithSameIdentifyFieldsInListReturnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        Activity activity = new Activity(new Title("Hot Spring"),
                new Priority(1),
                new Duration(3),
                new Location("Hokkaido"),
                new HashSet<>());
        assertTrue(uniqueActivityList.contains(activity));
    }

    @Test
    public void addNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void addDuplicateActivityListThrowsDuplicateActivityListException() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertThrows(DuplicateElementException.class, () -> uniqueActivityList.add(FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListNullTargetActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setElement(null,
                FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setElement(FIXED_ACTIVITY_1,
                        null));
    }

    @Test
    public void setActivityListTargetActivityListNotInListThrowsActivityListNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueActivityList.setElement(
                FIXED_ACTIVITY_1, FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListEditedActivityListIsSameActivityListSuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setElement(FIXED_ACTIVITY_1, FIXED_ACTIVITY_1);
        UniqueList<Activity> expectedActivityList = new UniqueList<>();
        expectedActivityList.add(FIXED_ACTIVITY_1);
        assertEquals(expectedActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListHasSameIdentitySuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        Activity activity = new Activity(new Title("Hot Spring"),
                new Priority(1),
                new Duration(3),
                new Location("Hokkaido"),
                new HashSet<>());
        uniqueActivityList.setElement(FIXED_ACTIVITY_1, activity);
        UniqueList<Activity> expectedUniqueActivityList = new UniqueList<>();
        expectedUniqueActivityList.add(activity);
        assertEquals(expectedUniqueActivityList, this.uniqueActivityList);
    }

    @Test
    public void setActivityListEditedActivityListHasDifferentActivityListSuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setElement(FIXED_ACTIVITY_1, FIXED_ACTIVITY_2);
        UniqueList<Activity> expectedUniqueActivityList = new UniqueList<>();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_2);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListEditedActivityListHasNonUniqueIdentityThrowsDuplicateActivityListException() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.add(FIXED_ACTIVITY_2);
        assertThrows(DuplicateElementException.class, () -> uniqueActivityList.setElement(
                FIXED_ACTIVITY_1, FIXED_ACTIVITY_2));
    }

    @Test
    public void removeNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void removeActivityListDoesNotExistThrowsActivityListNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueActivityList
                .remove(FIXED_ACTIVITY_3));
    }

    @Test
    public void removeExistingActivityListRemovesActivityList() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.remove(FIXED_ACTIVITY_1);
        UniqueList<Activity> expectedActivityList = new UniqueList<>();
        assertEquals(expectedActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListNullUniqueActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setElements((UniqueList<Activity>) null));
    }

    @Test
    public void setActivityListUniqueActivityListReplacesOwnListWithProvidedUniqueActivityList() {
        uniqueActivityList.add(FIXED_ACTIVITY_3);
        UniqueList<Activity> expectedUniqueActivityList = new UniqueList<>();
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setElements(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setUniqueActivityListNullListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setElements((List<Activity>) null));
    }

    @Test
    public void setUniqueActivityListListReplacesOwnListWithProvidedList() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        List<Activity> activityLists = Collections.singletonList(FIXED_ACTIVITY_3);
        uniqueActivityList.setElements(activityLists);
        UniqueList<Activity> expectedUniqueActivityList = new UniqueList<>();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_3);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListWithDuplicateActivityThrowsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivity = Arrays
                .asList(FIXED_ACTIVITY_1, FIXED_ACTIVITY_1);
        assertThrows(DuplicateElementException.class, () ->
                uniqueActivityList.setElements(listWithDuplicateActivity));
    }

    @Test
    public void asUnmodifiableObservableListModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testEquals() {
        UniqueList<Activity> expectedUniqueActivityList = new UniqueList<>();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertEquals(uniqueActivityList, expectedUniqueActivityList);

    }

    @Test
    public void testHashCode() {
        //Same Hash Code
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        UniqueList<Activity> list = new UniqueList<>();
        list.add(FIXED_ACTIVITY_1);
        assertEquals(list.hashCode(), uniqueActivityList.hashCode());

        //Different Hash code
        UniqueList<Activity> diffPl = new UniqueList<>();
        diffPl.add(FIXED_ACTIVITY_3);
        assertNotEquals(diffPl.hashCode(), uniqueActivityList.hashCode());
    }
}
