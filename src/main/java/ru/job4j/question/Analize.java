package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Analize  {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int delete = 0;

        Map<Integer, String> newMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (User user : previous) {
                String name = newMap.get(user.getId());

            if (name != null && !name.equals(user.getName())) {
                changed++;
            }
            if (name == null) {
                delete++;
            }
        }
        added = current.size() - (previous.size() - delete);
        return new Info(added, changed, delete);
    }
}
