import java.util.*;
class Solution {
    String preceding;
    List<Character> characterList;

    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        preceding = skill;
        characterList = new ArrayList<>();
        for (int i = 0; i < skill.length(); i++) {
            characterList.add(skill.charAt(i));
        }
        for (String skillTree : skill_trees) {
            if (canSkillTree(skillTree)) {
                count++;
            }
        }
        return count;
    }
    private boolean canSkillTree(String skillTree) {
        int precedingSkillIndex = 0;

        for (int i = 0; i < skillTree.length(); i++) {
            char nowSkill = skillTree.charAt(i);
            if (preceding.charAt(precedingSkillIndex) != nowSkill && characterList.contains(nowSkill)) {
                return false;
            }
            if (preceding.charAt(precedingSkillIndex) == nowSkill) {
                precedingSkillIndex++;
            }
            if (precedingSkillIndex == preceding.length()) {
                return true;
            }
        }
        return true;
    }
    
}