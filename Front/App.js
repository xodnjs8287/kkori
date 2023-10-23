import 'react-native-gesture-handler';
import React, { useCallback, useEffect, useState } from 'react';
import { StyleSheet, View, Text,  } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import MainPage from "./screens/MainPage";
import Test from "./screens/Test";
import Walk from "./screens/Walk";
import Chat from "./screens/Chat";
import MyPage from "./screens/MyPage";

// 하단 네비바
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
      <Tab.Screen name="Back" component={MainPage} options={{ title: '뒤로가기' }} />
      <Tab.Screen name="Main" component={MainPage} options={{ title: '홈' }} />
      <Tab.Screen name="WalkStatus" component={Walk} options={{ title: '산책현황' }} />
      <Tab.Screen name="Chat" component={Chat} options={{ title: '채팅' }} />
      <Tab.Screen name="UserInfo" component={MyPage} options={{ title: '내정보' }} />
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
