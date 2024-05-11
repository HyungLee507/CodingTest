#include <string>
#include <vector>
#include <algorithm>

using namespace std;

enum Direction { Left, Up, Right, Down };

class Edge;
class Node;

class Edge
{
private:
    Direction d;
    Node* to;
public:
    bool visited;
    
    Edge(Direction d);

    void SetNode(Node* n);
    bool MoveLightNextNode(vector<int>& cycle);
};

class Node
{
public:
    enum Type { S, L, R };

private:
    const int idx;
    Type type;
    Edge e[4];              // 0: west, 1: north, 2: east, 3: south
public:
    Node(int idx, Type t);

    void SetEdge(Node* west, Node* north, Node* east, Node* south);
    bool MoveLightNextEdgeFrom(Direction d, vector<int>& cycle);
};

Edge::Edge(Direction d)
{
    this->d = d;
    visited = false;
    to = nullptr;
}

void Edge::SetNode(Node* n)
{
    to = n;
}

bool Edge::MoveLightNextNode(vector<int>& cycle)
{
    if (visited)
        return false;
    else
    {
        visited = true;
        to->MoveLightNextEdgeFrom(d, cycle);
        return true;
    }
}

Node::Node(int idx, Type t) : idx{ idx }, e{ Direction::Left, Direction::Up, Direction::Right,Direction::Down }
{
    type = t;
}

void Node::SetEdge(Node* west, Node* north, Node* east, Node* south)
{
    e[0].SetNode(west);
    e[1].SetNode(north);
    e[2].SetNode(east);
    e[3].SetNode(south);
}

bool Node::MoveLightNextEdgeFrom(Direction d, vector<int>& cycle)
{
    bool isSuccess{};
    cycle.push_back(idx);

    switch (type)
    {
    case S:
        isSuccess = e[d].MoveLightNextNode(cycle);
        break;
    case L:
        isSuccess = e[(d + 3) % 4].MoveLightNextNode(cycle);
        break;
    case R:
        isSuccess = e[(d + 1) % 4].MoveLightNextNode(cycle);
        break;
    }

    if (isSuccess)
        return true;
    else
    {
        cycle.pop_back();
        return false;
    }
}


inline Node::Type ConvType(char c)
{
    switch (c)
    {
    case 'S':
        return Node::Type::S;
    case 'L':
        return Node::Type::L;
    case 'R':
        return Node::Type::R;
    }
}

inline int Conv2to1(int row, int col, int colLen)
{
    return colLen * row + col;
}

vector<int> solution(vector<string> grid) {
    vector<int> answer;

    vector<Node> NodeList;

    int idx{};

    for (string s : grid)                   // 노드 생성
    {
        for (char c : s)
        {
            NodeList.push_back(Node{ idx, ConvType(c) });
            ++idx;
        }
    }

    int col{ int(grid[0].size()) }, row{ int(grid.size()) };
    for (int i{}; i < row; ++i)             // 노드의 엣지 설정
    {
        for (int j{}; j < col; ++j)
        {
            NodeList[i * col + j]
                .SetEdge(&NodeList[Conv2to1(i, (j + col - 1) % col, col)], &NodeList[Conv2to1((i + row - 1) % row, j, col)],
                    &NodeList[Conv2to1(i, (j + 1) % col, col)], &NodeList[Conv2to1((i + 1) % row, j, col)]);
        }
    }

    for (Node& node : NodeList)
    {
        for (int i{}; i < 4; ++i)
        {
            vector<int> cycle{ };
            if (node.MoveLightNextEdgeFrom(Direction(i), cycle))
                answer.push_back(int(cycle.size()));
        }
    }

    sort(answer.begin(), answer.end());

    return answer;
}