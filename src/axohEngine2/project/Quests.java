package axohEngine2.project;

public class Quests {
	/**
	 * 
	 * Janine Jay - RMA DIABETO These are the texts for the dialogue of all of
	 * the characters as well as the text for the quest prompts.
	 *
	 */

	// 66 dialogue boxes needed
	// There are breaks where the quests should be inserted
	String[] hoffmanDialogue = new String[]{
			"Hoffman: Hello I am Professor Hoffman"
	};
	String[] duncanDialogue = new String[]{
		"Duncan: Hello I am Professor Duncan"
	};
	String[] blakeDialogue = new String[]{
			"Blake: Hello I am Professor Blake"
	};
	String[] dialogue = new String[] {

			"Mom: Wake up honey, it's time to get up!", "You: Ugh mom I don't want to. It's too early.",
			"Mom: I need you to go outside and go take care of a mouse problem.",
			"Dad: Wait honey, take this keyboard to help you! You can take this health potion just in case you get hurt!",
			"You: Okay I'll be back", "Quest 0: Defeat the mice in the garden.",

			"You: Can I go back to bed now?",
			"Dad: Honey you can't spend your entire life in bed, you need to see the world outside of tumblr!",
			"You: Hahaha dad very funny. That doesn't exist'...", "Mom: I know but ' ' ' ' ",
			"You: But what? Mom? MOOMMM?", "You: Dad! Mom's screen went blank!", "You: Dad?",
			"You: Oh god. What am I going to do! Wait what's this?", "Best Buy, 470 Lewis Avenue, Meriden, CT 06450",
			"You: Well I guess this is worth a try. I'm going to find this ancient land called Best Buy. *I wonder what they are buying the best of'.*",
			"Quest 1: What does that flyer over there say?",

			"You: Hm, I wonder if this Professor Blake fellow could help me.", "NPC: Pfft, girls can't be programmers.",
			"Quest 2: Find a male disguise in one of the chests.",

			"You:Well at least this disguise will get me a better chance at getting help for my parents.",
			"Quest 3: Go find Professor Blake and talk to him about internship.",

			"Blake: 'Ugh another boy! I am so sick of people mindlessly following commercial advice like I want to be a "
					+ "Computer Scientist Barbie and think that boys are the only one who can program! "
					+ "Half of the world's remaining population is turned...",
			"Blake: ...away from Science and Technology because of their gender! Go away you.' ",
			"Blake: Eeh-gasp! It's a girl!",
			"You: Yes it's true, I have disguised myself as a boy in order to have an easier time getting help. "
					+ "You need to help me, my parents have gone blank! I'm trying to find Best Buy so that they can fix them.",
			"Blake: Hmmm. Best Buy you say? I think I heard of that a few years ago' I'll tell you what. I'll go look up "
					+ "in my address book if I know a Best Buy and you go find something for me. I've lost one of my frisbees. "
					+ "Unknown to most, the skills you learn...",
			"Blake: ...mastering the disk are pretty much universal. Go off and find my " + "disk then return to me.",
			"Quest 4: Go and find an frisbee in one of the chests.",

			"Blake: Excellent! Now do you know how to properly use one of these? These are weapons for battling. When "
					+ "you have defeated three enemies with the disk, you will have mastered this skill. Go on, try it!",
			"Quest 5: Defeat three monsters battling using the frisbee.",

			"Blake: Well done! Now I couldn't find a Best Buy address but I think a man named Professor Duncan"
					+ " might know where it is. Go look him up. Here, please take a snack, it will fuel you on your journey."
					+ " And take this golden disk. It is an excellent long... ",
			"Blake: ...range weapon to help you defeat enemies.", "You: Thank you!",
			"Quest 6: Go find Professor Duncan.",
			"Duncan: Ah hello, welcome to Algor- ' I mean welcome! What can I help you with?",
			"You: I am trying to find some land called Best Buy so that they can help fix my parents.",
			"Duncan: Hmmmmm. I might know where that is. Let me run it through my Miracle Search program. "
					+ "It might take a while. Say, would you like to learn about Advanced Mathematics?",
			"You: Bring it on!",
			"Quest 7: Prove by induction: Every solved problem whose answer can be checked "
					+ "quickly by a computer also be quickly solved by a computer? "
					+ "P and NP are the two types of maths problems referred to: P problems are fast "
					+ "for computers to solve, and so are...",
			"Quest 7: ...considered 'easy'. NP problems are fast (and so 'easy') "
					+ "for a computer to check, but are not necessarily easy to solve.",

			"You: Ummm'. I have absolutely no idea how to solve this. Yes?",
			"Duncan: Well showing your work is a majority of the points but you did get the answer right!",
			"You: Does this mean I passed?", "Duncan: Let me do some grading and I'll get back to you. "
					+ "In the meantime, go help this student with his assignment.",
			"Quest 8: Go talk to classmate",

			"NPC: Oh god, this is due tomorrow and for the life of me I cannot find the "
					+ "files I was working on! Please, can you help me look????",
			"You: Sure", "Quest 9: Go to chests and find 'the files.",

			"NPC: 'Thank you so much! I thought for sure I was going to fail. "
					+ "Oh and by the way, Duncan is looking for you.",
			"Quest 10: Go back to Duncan's office.",

			"Duncan: Hey there, so I'm done with the grading and no one got a perfect score except for "
					+ "one student from Greece... Anyways, due to the curve, you went from a 37 to a 95! Now about"
					+ " that search for Best Buy, the algorithm didn't find anything... ",
			"Duncan: ..Unless it's in a place called"
					+ " null ...  Anyways there is one more person you can check with who might know it is. They call"
					+ " him' 'The Hoff'",
			"You: Yes! Thank you Professor Duncan. I'm off to find the legendary Hoff.",
			"Quest 11: Go find Professor Hoffman.",

			"Hoffman: Welcome, I am Professor Hoffman, what is it you seek?",
			"You: I am on a quest to find Best Buy so that I can get my parents fixed.",
			"Hoffman: Hm, by your voice, you don't sound like a boy' wait a minute' you're a girl! "
					+ "Well, in all my time in teaching hundreds and hundreds of students, you must be the 9th female.",
			"You: Cool.", "Hoffman: If you want to find Best Buy' you must answer the following elephant riddles.",
			"You: ...I don't know if I have learned enough in order to do that'",
			"Hoffman: What if I give the top level code?", "Quest 12: Answer 3 elephant riddles",
			"What does Tarzan say when he sees a herd of elephants in the distance?" + "A: 'Are those elephants?''"
					+ "B: 'Quick! Get some mice!''" + "C: 'Look, a herd of elephants in the distance'",
			"What does Tarzan say when he sees a herd of elephants with sunglasses"
					+ "A: Nothing. He doesn't recognize them." + "B: 'Wow, those are some cool elephants'"
					+ "C: 'Wow it is sunny out today'",
			"What does Tarzan say when he sees a herd of giraffes in the distance?"
					+ "A: 'Look, a herd of giraffes in the distance.'"
					+ "B: 'Haha! You fooled me once with those disguises, but not this time!'"
					+ "C: 'Wow, there are a lot of animals out here'",

			"Hoffman: Excellent! You have completed my test! Please follow me!",
			"Quest 13: Follow Professor Hoffman to an abandoned Best Buy.",

			"You: Yay! I found Best Buy! Wait' It's closed!",
			"Hoffman: Yes however you have all the skills to fix your parents yourself!",
			"You: Hmmm' That's right! I've mastered frisbee, complex math, and elephant riddles. "
					+ "That's all you need to be an amazing programmer. I can do this!",
			"Parents: Yay! You've cured us!",
			"You: And doing it myself I saved so much money that I would have just given to Geek Squad!" };

	public String[] getDialogue() {
		return dialogue;
	}
	public String[] getHoffmanDialogue(){
		return hoffmanDialogue;
	}
	public String[] getBlakeDialogue(){
		return blakeDialogue;
	}
	public String[] getDuncanDialogue(){
		return duncanDialogue;
	}
	// 16 Quest boxes needed
	/**
	 * String [] quest = new String []{
	 * "Quest 0: Defeat the mice in the garden.",
	 * "Quest 1: What does that flyer over there say?",
	 * "Quest 2: Find a male disguise in one of the chests.",
	 * "Quest 3: Go find Professor Blake and talk to him about internship.",
	 * "Quest 4: Go and find an frisbee in one of the chests.",
	 * "Quest 5: Defeat three monsters battling using the frisbee.",
	 * "Quest 6: Go find Professor Duncan.",
	 * "Quest 7: Prove by induction: Every solved problem whose answer can be checked "
	 * + "quickly by a computer also be quickly solved by a computer? " +
	 * "P and NP are the two types of maths problems referred to: P problems are fast "
	 * + "for computers to solve, and so are considered 'easy'. NP problems are
	 * fast (and so 'easy') " +
	 * "for a computer to check, but are not necessarily easy to solve.",
	 * "Quest 8: Go talk to classmate",
	 * "Quest 9: Go to chests and find 'the files.",
	 * "Quest 10: Go back to Duncan's office.",
	 * "Quest 11: Go find Professor Hoffman.",
	 * "Quest 12: Answer 3 elephant riddles",
	 * "What does Tarzan say when he sees a herd of elephants in the distance?"
	 * + "A: 'Are those elephants?''" + "B: 'Quick! Get some mice!''" +
	 * "C: 'Look, a herd of elephants in the distance'",
	 * "What does Tarzan say when he sees a herd of elephants with sunglasses" +
	 * "A: Nothing. He doesn't recognize them." +
	 * "B: 'Wow, those are some cool elephants'" +
	 * "C: 'Wow it is sunny out today'",
	 * "What does Tarzan say when he sees a herd of giraffes in the distance?" +
	 * "A: 'Look, a herd of giraffes in the distance.'" +
	 * "B: 'Haha! You fooled me once with those disguises, but not this time!'"
	 * + "C: 'Wow, there are a lot of animals out here'",
	 * "Quest 13: Follow Professor Hoffman to an abandoned Best Buy.", };
	 **/

}