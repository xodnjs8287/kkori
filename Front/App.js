import 'react-native-gesture-handler';
import React from 'react';
import { StyleSheet, View, Text } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Main from "./screens/Main";

const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

function HomeScreen() {
  return (
    <View style={styles.center}>
      <Text>홈</Text>
    </View>
  );
}

function WalkStatusScreen() {
  return (
    <View style={styles.center}>
      <Text>산책현황</Text>
    </View>
  );
}

function ChatScreen() {
  return (
    <View style={styles.center}>
      <Text>채팅</Text>
    </View>
  );
}

function UserInfoScreen() {
  return (
    <View style={styles.center}>
      <Text>내정보</Text>
    </View>
  );
}

function TabNavigator() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Back" component={Main} options={{ title: '뒤로가기' }} />
      <Tab.Screen name="Home" component={Main} options={{ title: '홈' }} />
      <Tab.Screen name="WalkStatus" component={Main} options={{ title: '산책현황' }} />
      <Tab.Screen name="Chat" component={Main} options={{ title: '채팅' }} />
      <Tab.Screen name="UserInfo" component={Main} options={{ title: '내정보' }} />
    </Tab.Navigator>
  );
}

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Main" component={TabNavigator} options={{ headerShown: false }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  }
});
