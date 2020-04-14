import React from 'react';
import ReactDOM from 'react-dom';

const element = document.querySelector('#root');

/*  -------------------------------------------------------------------------------------  */

// 主题样式
const themes = {
  light: {
    foreground: '#000000',
    background: '#eeeeee',
  },
  dark: {
    foreground: '#ffffff',
    background: '#222222',
  },
}

// context
const ThremeContext = React.createContext(themes.dark);

// 带样式的button
class ThemeButton extends React.Component {
  static contextType = ThremeContext;
  render () {
    let props = this.props;
    let theme = this.context;
    return (
      <button {...props} style={{backgroundColor: theme.background}}></button>
      // <button onClick={props.changeTheme} style={{backgroundColor: theme.background}}>{props.children}</button>
    );
  }
}

// 使用 ThemeButton 的中间组件
function Toolbar(props) {
  // return (
  //   <ThemeButton changeTheme={props.changeTheme}>
  //       Change Theme
  //   </ThemeButton>
  // );
  return (
    <ThemeButton onClick={props.changeTheme}>
      Change Theme
    </ThemeButton>
  );
}

class App extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      theme: themes.light
    }
  }
  changeTheme = () => {
    this.setState((state, props) => {
      return {
        theme: state.theme === themes.dark 
          ? themes.light
          : themes.dark
      }
    });
  }
  render (){
    return (
      <ThremeContext.Provider value={this.state.theme}>
          <Toolbar changeTheme={this.changeTheme}></Toolbar>
      </ThremeContext.Provider>
    );
  }
}

ReactDOM.render(<App/>, element);


