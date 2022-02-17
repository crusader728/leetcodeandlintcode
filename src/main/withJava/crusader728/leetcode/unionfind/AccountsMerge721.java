package withJava.crusader728.leetcode.unionfind;

import java.util.*;

public class AccountsMerge721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parents = new HashMap<>();
        Map<String, String> emailNameMap = new HashMap<>();
        Map<String, TreeSet<String>> children = new HashMap<>();

        for(List<String> info: accounts) {
            for(int i = 1; i < info.size(); ++i) {
                parents.put(info.get(i), info.get(i));
            }
        }
        
        for(List<String> info: accounts) {
            String name = info.get(0);
            for(int i = 1; i < info.size(); ++i) {
                emailNameMap.put(info.get(i), name);
                if(i == 1) {
                    parents.put(info.get(i), find(info.get(i), parents));
                } else {
                    String parent1 = find(info.get(1), parents);
                    String parent2 = find(info.get(i), parents);
                    if(parent1.equals(info.get(1)) && parent2.equals(info.get(i))) {
                        parents.put(info.get(i), info.get(1));
                    } else if(parent1.equals(info.get(1))) {
                        parents.put(parent1, parent2);
                    } else if(parent2.equals(info.get(i))) {
                        parents.put(parent2, parent1);
                    } else {
                        parents.put(parent2, parent1);
                    }
                }
            }
        }

        for(String email: parents.keySet()) {
            parents.put(email, find(email, parents));
        }

        for(Map.Entry<String, String> entry: parents.entrySet()) {
            String child = entry.getKey();
            String parent = entry.getValue();
            if(!children.containsKey(parent)) {
                children.put(parent, new TreeSet<>());
            }
            children.get(parent).add(child);
        }


        List<List<String>> result = new ArrayList<>();
        for(String root: children.keySet()) {
            List<String> info = new ArrayList<>();
            info.add(emailNameMap.get(root));
            info.addAll(children.get(root));
            result.add(info);
        }
        return result;
    }

    private String find(String email, Map<String, String> parents) {
        if(!parents.get(email).equals(email)) {
            parents.put(email, find(parents.get(email), parents));
            return parents.get(email);
        } else {
            return email;
        }
    }

    public static void main(String[] args) {
        AccountsMerge721 accountsMerge721 = new AccountsMerge721();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_network@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accountsMerge721.accountsMerge(accounts);
    }

}
