import java.util.*;
class Solution {
    
    private static class Log{
        String state;
        User user;
        
        public Log(String state, User user){
            this.state = state;
            this.user = user;
        }
        private String getLogMessage(){
            if(this.state.equals("Enter")){
                return user.nickname + "님이 들어왔습니다.";
            }else{
                return user.nickname + "님이 나갔습니다.";
            }
        }
    
        
    }
    private static class User{
        String uid;
        String nickname;
        public User(String uid, String nickname){
            this.uid=uid;
            this.nickname = nickname;
        }
        private void changeNickname(String newName){
            this.nickname = newName;
        }
    }
    List<Log> logs = new ArrayList<>();
    HashMap<String,User> users = new HashMap<>(); 
    
    public String[] solution(String[] record) {    
        for(int i=0; i<record.length; i++){
            performInstruction(record[i]);
        }
        String[] answer = new String[logs.size()];
        for(int i=0; i<answer.length; i++){
            Log log = logs.get(i);
            answer[i] = log.getLogMessage();
        }
        return answer;
    }
    private  void performInstruction(String record) {
        String[] split = record.split(" ");
        String instruction = split[0];
        String uid = split[1];
        User user = findByUid(uid);
        if (instruction.equals("Change")) {
            String nickname = split[2];
            user.changeNickname(nickname);
        } else if (instruction.equals("Leave")) {
            logs.add(new Log(instruction, user));
        } else {
            // enter의 경우
            String nickname = split[2];
            if (user == null) {
                user = new User(uid, nickname);
                // users.add(user);
                users.put(uid, user);
            }
            if (!user.nickname.equals(nickname)) {
                user.changeNickname(nickname);
            }
            logs.add(new Log(instruction, user));
        }
    }

    private User findByUid(String uid) {
        if(users.containsKey(uid)){
            return users.get(uid);
        }
        return null;
    }
    
}