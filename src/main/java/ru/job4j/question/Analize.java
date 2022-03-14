package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Analize  {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> newMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
                String name = newMap.get(user.getId());
            if (!newMap.containsKey(user.getId())) {
                info.added++;
            }
            if (name != null && !name.equals(user.getName())) {
                info.changed++;
            }
            if (name == null) {
                info.delete++;
            }
        }
        return new Info(info.added, info.changed, info.delete);
    }
}
