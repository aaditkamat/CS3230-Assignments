#include <iostream>
#include <vector>
int calculate_minimum(int maxLevel, int amountRequired, std::vector<int> values, std::vector<int> costs) {
	return 0;
}
int main(){
	int testCases;
	std::cin >> testCases;
	for (int tc = 0; tc < testCases; tc++) {
		int maxLevel;
		std::cin >> maxLevel;
		int amountRequired;
		std::cin >> amountRequired;
		std::vector<int> values;
		for (int i = 0; i <= maxLevel; ++i) {
			int value;
			std::cin >> value;
			values.push_back(value);
		}
		std::vector<int> costs;
		for (int i = 0; i <= maxLevel; ++i) {
			int cost;
			std::cin >> cost;
			costs.push_back(cost);
		}
		std::cout << calculate_minimum(maxLevel, amountRequired, values, costs) << std::endl;
	}
	return 0;
}
