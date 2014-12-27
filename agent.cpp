/* Design of the project */

/*

Reading Input file: Read and construct Data Structure

1) Read clauses into nodes (Node(clausename,left arg,rightarg)) Node* n = Encode(clause);
2) Construct table of form lhs=>rhs. RHS is just a Node*.
But, lhs might be a conjuction of clauses, in our case: (Node* & Node* & ... )
3) Parser: A generic parser to read a line of input, identify lhs and rhs. And also,
this should break lhs in to multiple nodes,if any and then update table
in the form lhs->rhs.
4) KB => Table data structure =>  2 coloumns, rhs is just a node, our conclusion.
lhs can be either empty,one node or multiple nodes.
multiple nodes stored as array of nodes.
5) Inference Engine => One who runs our algorithm and finds if the query can be
proven using KB or not.
*/

#define _CRT_SECURE_NO_WARNINGS  //disable secure warnings
#include<iostream>
#include<string>
#include<vector>
#include <fstream>
#include<sstream>

using namespace std;

//Node structure to hold our Predicates
class Node {
	string name;
	string left;
	string right;
public:
	void setname(string s){
		name = s;
	}
	void setleft(string s){
		left = s;
	}
	void setright(string s){
		right = s;
	}
	string getname(){
		return name;
	}
	string getleft(){
		return left;
	}
	string getright(){
		return right;
	}
};

struct Knowledge_Base{
	vector<Node*> pred;
	Node* conclusion;
};
int counter = 0;
// Defining an empty KB
Knowledge_Base* KB = new Knowledge_Base[20];
Knowledge_Base* tempKB = new Knowledge_Base[20];
int number_of_clauses = 0;
int flag = 0,num_theta=0,life_is_hard=0;
string Query, theta = "", possible_theta[20];

class Component{

public:
	ofstream out_file;
	string trim_string(string str)
	{
		string str_req = str;
		if (str == "") return str;
		int k = str.size();
		if (str_req[0] == ' '){
			str_req = str_req.substr(1, k - 1);
		}
		if (str_req[k - 1] == ' '){
			str_req = str_req.substr(0, k - 2);
		}
		return str_req;
	}
	Node* encode(string query){
		//you get a Node with filled details
		Node* temp = new Node();
		int pos;
		char *dup = strdup(query.c_str());
		temp->setname(strtok(dup, "("));
		if ((pos = query.find(",")) != -1){
			temp->setleft(strtok(NULL, ","));
			temp->setright(strtok(NULL, ")"));
		}
		else {
			temp->setleft(strtok(NULL, ")"));
			temp->setright("");
		}
		temp->setname(trim_string(temp->getname()));
		temp->setleft(trim_string(temp->getleft()));
		temp->setright(trim_string(temp->getright()));
		return temp;
	}
	void Parser(string query){
		Node* KB_node = new Node();
		std::size_t pos = query.find("=>");
		std::string delimiter = "=>";
		string clause[2], predicate[20];
		char *dup;
		int i = 0;
		if (pos != -1){
			dup = strdup(query.c_str());
			string token = strtok(dup, "=>");
			while (dup) {
				clause[i] = dup;
				i++;
				dup = strtok(NULL, "=>");
			}
			//clause[0]=lhs, clause[1]=rhs
			dup = strdup(clause[0].c_str());
			token = strtok(dup, "&");
			i = 0;
			while (dup) {
				predicate[i] = dup;
				i++;
				dup = strtok(NULL, "&");
			}
			int n = i;
			for (i = 0; i < n; i++)
			{
				Node* temp = new Node();
				dup = strdup(predicate[i].c_str());
				temp->setname(strtok(dup, "("));
				if ((pos = predicate[i].find(",")) != -1){
					temp->setleft(strtok(NULL, ","));
					temp->setright(strtok(NULL, ")"));
				}
				else{
					temp->setleft(strtok(NULL, ")"));
					temp->setright("");
				}
				temp->setname(trim_string(temp->getname()));
				temp->setleft(trim_string(temp->getleft()));
				temp->setright(trim_string(temp->getright()));
				KB[counter].pred.push_back(temp);
				tempKB[counter].pred.push_back(temp);
			}
			//handling clause[1]
			Node* temp = new Node();
			dup = strdup(clause[1].c_str());
			temp->setname(strtok(dup, "("));
			if ((pos = clause[1].find(",")) != -1){
				temp->setleft(strtok(NULL, ","));
				temp->setright(strtok(NULL, ")"));
			}
			else{
				temp->setleft(strtok(NULL, ")"));
				temp->setright("");
			}
			temp->setname(trim_string(temp->getname()));
			temp->setleft(trim_string(temp->getleft()));
			temp->setright(trim_string(temp->getright()));
			KB[counter].conclusion = temp;
			tempKB[counter].conclusion = temp;
			counter++;
			//parse predicates and clause[1] to construct KB

		}
		else{
			pos = query.find("&");
			if (pos == -1){
				//its just a predicate without =>
				char *dup = strdup(query.c_str());
				KB_node->setname(strtok(dup, "("));
				if ((pos = query.find(",")) != -1){
					KB_node->setleft(strtok(NULL, ","));
					KB_node->setright(strtok(NULL, ")"));
				}
				else{
					KB_node->setleft(strtok(NULL, ")"));
					KB_node->setright("");
				}
				//KB[counter].pred = {};
				KB_node->setname(trim_string(KB_node->getname()));
				KB_node->setleft(trim_string(KB_node->getleft()));
				KB_node->setright(trim_string(KB_node->getright()));
				KB[counter].conclusion = KB_node;
				tempKB[counter].conclusion = KB_node;
				counter++;
			}
			else{
				dup = strdup(query.c_str());
				string token = strtok(dup, "&");
				i = 0;
				while (dup) {
					predicate[i] = dup;
					i++;
					dup = strtok(NULL, "&");
				}
				int n = i;
				for (i = 0; i < n; i++)
				{
					Node* temp = new Node();
					dup = strdup(predicate[i].c_str());
					temp->setname(strtok(dup, "("));
					if ((pos = predicate[i].find(",")) != -1){
						temp->setleft(strtok(NULL, ","));
						temp->setright(strtok(NULL, ")"));
					}
					else{
						temp->setleft(strtok(NULL, ")"));
						temp->setright("");
					}
					//KB[counter].pred = {};
					temp->setname(trim_string(temp->getname()));
					temp->setleft(trim_string(temp->getleft()));
					temp->setright(trim_string(temp->getright()));
					KB[counter].conclusion = temp;
					tempKB[counter].conclusion = temp;
					counter++;
				}
			}
		}
	}

	// read input
	void read(string filename){
		ifstream inFile;
		inFile.open(filename.c_str()); // .c_str() converts C++ string var to a C style string equivalent
		string read_line, str_temp;
		int i = 1, j, k = 0, l, count = 0;
		while (getline(inFile, read_line)){
			//Debug statement
			//cout << "\n Read line for :" << i << "   : " << read_line<<"\n";
			istringstream content(read_line);
			switch (i){
			case 1:  Query = read_line;
				i++;
				break;
			case 2: //istringstream content(read_line);
				content >> number_of_clauses;
				//number_of_clauses = stoi(read_line, nullptr, 0);
				i++;
				break;
			case 3: if (count != number_of_clauses){
				str_temp = read_line;
				Parser(str_temp);
				count++;
			}
					else
						break;

			}
		}
		inFile.close();
	}
	bool Subst(Node* n1, Node* n2){
		bool value = true;
		//theta = "";
		if (n1->getleft() == "x" && n1->getright() == "x"){
			if (n2->getleft() == n2->getright()){
				theta = n2->getleft();
				flag = 1;
			}
			else{
				//failure condition
				value = false;
				return value;
			}
		}
		if (n1->getleft() == "x"){
			if (n2->getleft() != "x"){
				if (flag == 0){
					theta = n2->getleft();
					flag = 1;
				}
				else{
					//if (theta != n2->getleft()) return false;
				}

			}
		}
		if (n1->getright() == "x"){
			if (n2->getright() != "x"){
				if (flag == 0){
					theta = n2->getright();
					flag = 1;
				}
				else{
					//if (theta != n2->getright()) return false;
				}

			}
		}
		return value;
	}
	bool Subst(vector<Node*> n_queue, Node* n){
		//theta = "";
		bool value = true;
		for (std::vector<Node*>::iterator it = n_queue.begin(); it != n_queue.end(); ++it){
			Node *q = *it;
			if (q->getleft() == "x"){
				if (n->getleft() != "x"){
					if (flag == 0){
						theta = n->getleft();
						flag = 1;
					}
					else{
						//if (theta != n->getleft()) return false;
					}
				}
			}
			if (q->getright() == "x"){
				if (n->getright() != "x"){
					if (flag == 0){
						theta = n->getright();
						flag = 1;
					}
					else{
						//if (theta != n->getright()) return false;
					}

				}
			}
		}
		return value;
	}

	bool Is_a_match(Node* m1, Node* m2){
		if (m1->getleft() == m2->getleft() && m1->getname() == m2->getname() && m1->getright() == m2->getright()) {
			return true;
		}
		return false;
	}


	bool match_assertion(Node* query){
		for (int i = 0; i < counter; i++){
			if (KB[i].pred.size() == 0){
				// This is an assertion
				if (Is_a_match(KB[i].conclusion, query)){
					return true;
				}
			}
		}
		return false;
	}
	bool match_rule(Node* rhs, Node* query){
		if (rhs->getname() == query->getname()){
			if ((rhs->getleft() == "x" || rhs->getleft() == query->getleft()) && (rhs->getright() == query->getright() || rhs->getright() == "x")){
				if (Subst(rhs, query)){
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	Node* Unify(Node* n, string theta){
		Node* ret = new Node();
		ret->setname(n->getname());
		ret->setleft(n->getleft());
		ret->setright(n->getright());
		if (n->getleft() == "x")
			ret->setleft(theta);
		if (n->getright() == "x")
			ret->setright(theta);
		return ret;
	}

	bool theta_finder(Knowledge_Base iKB, Node* query){
		bool value = false;
		//check with any of the facts and get the value of X
		for (int p = 0; p < counter; p++){
			if (KB[p].pred.size() == 0){
				if (query->getname() == KB[p].conclusion->getname()){
					if ((query->getleft() == "x" && query->getright() == "x") && (KB[p].conclusion->getleft() == KB[p].conclusion->getright())){
						theta = KB[p].conclusion->getleft();
						flag = 1;
						possible_theta[num_theta++] = theta;
						value = true;
					}
				}
				else if (query->getleft() == "x" && (query->getright() == KB[p].conclusion->getright())) {
					theta = KB[p].conclusion->getleft();
					flag = 1;
					possible_theta[num_theta++] = theta;
					value = true;
				}
				else if (query->getright() == "x" && (query->getleft() == KB[p].conclusion->getleft())){
					theta = KB[p].conclusion->getright();
					flag = 1;
					possible_theta[num_theta++] = theta;
					value = true;
				}
			}
		}
		if (flag == 1) return value;
		if (iKB.pred.size() == 0){
			if ((query->getleft() == "x" && query->getright() == "x") && (iKB.conclusion->getleft() == iKB.conclusion->getright())){
				theta = iKB.conclusion->getleft();
				flag = 1;
				possible_theta[num_theta++] = theta;
				value = true;
			}
			else if (query->getleft() == "x" && (query->getright() == iKB.conclusion->getright())) {
				theta = iKB.conclusion->getleft();
				flag = 1;
				possible_theta[num_theta++] = theta;
				value = true;
			}
			else if (query->getright() == "x" && (query->getleft() == iKB.conclusion->getleft())){
				theta = iKB.conclusion->getright();
				flag = 1;
				possible_theta[num_theta++] = theta;
				value = true;
			}
		}
		else {
			// I dont need to traverse through all predicates as my motive here is to just find one x
			//hence I go through one and only one predicate=> lets choose the first one, left most :)
			Node* leftmost = iKB.pred.front();
			//go through KB again to find theta
			for (int y = 0; y < counter && !value; y++){
				if (KB[y].conclusion->getname() == leftmost->getname()){
					value = theta_finder(KB[y],leftmost);
				}
			}
		}
		return value;
	}

	// The main controller routine - Driver of our Engine

	bool Infer_Engine(Node* query){
		for (int i = 0; i < counter; i++){
			if (match_assertion(query)){
				return true;
			}
			else if (match_rule(KB[i].conclusion, query)){
				Node *t;
				if (theta != ""){
					t = Unify(KB[i].conclusion, theta);
				}
				else{
					//traverse a chain to get the theta
					if (theta_finder(KB[i], query)){
						if (num_theta > 0) {
							life_is_hard = 1;
							bool value_t = false;
							for (int i = 0; i < num_theta && (!value_t); i++){
								theta = possible_theta[i];
								Node *t = Unify(query, theta);
								// creating a proxy engine that traverses through all possibilities of theta
								if (Infer_Engine(t)){
									return true;
								}
								for (int h = 0; h < counter; h++){
									KB[h] = tempKB[h];
								}
							}
						}
					}
				}
				t = Unify(KB[i].conclusion, theta);
				// Validate rules and substitute
				if (Subst(KB[i].pred, t)){
					bool value = true;
					for (std::vector<Node*>::iterator it = KB[i].pred.begin(); it != KB[i].pred.end() && value; ++it){
						*it = Unify(*it, theta);
						value = value & Infer_Engine(*it);
						//debugging purpose
						/*Node* w = *it;
						cout << "Rule matched and its value\n";
						cout << "==========================\n";
						cout << w->getname() << "(" << w->getleft();
						if (w->getright() != ""){
							cout << "," << w->getright();
						}
						cout << ")";
						cout << ":=" << value;
						cout << "\n=========================\n";*/
					}
					return value;
				}
			}
		}
		return false;
	}
};

int main(){
	Component* c = new Component();
	//c->Parser("LostWeight(x)&Diagnosis(x,LikelyInfected)=>Diagnosis(x,Infected)");
	c->read("input.txt");
	/*cout << "\n ===================KB==============================\n";
	for (int i = 0; i < counter; i++){
		for (std::vector<Node*>::iterator it = KB[i].pred.begin(); it != KB[i].pred.end(); ++it){
			cout << "|&| ";
			Node *t = *it;
			cout << t->getname() << "-";
			cout << t->getleft() << "-";
			cout << t->getright();
		}
		cout << "=> |";
		cout << KB[i].conclusion->getname() << "-";
		cout << KB[i].conclusion->getleft() << "-";
		cout << KB[i].conclusion->getright() << "|\n";
	}
	cout << "========================================================\n";*/
	Node *query = c->encode(Query);
	bool result = c->Infer_Engine(query);
	c->out_file.open("output.txt");
	if (result)
		c->out_file << "TRUE\n";
	else
		c->out_file << "FALSE\n";
	c->out_file.close();
	cout << result;
//	cin.get();
}

