import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Button, Card, CardText, CardTitle, Col, Container, Input, Row} from "reactstrap";
import TodoItem from "./TodoItem";
import InputAdd from "./InputAdd";

class Todo extends Component {

    state = {
        tasks: [],
        showInput: false,
        nameLocal:'taskLocal'
    }
    componentDidMount() {
        let tasks=localStorage.getItem(this.state.nameLocal)
        tasks=JSON.parse(tasks);
        if (tasks) {
            this.setState({tasks})
        }
    }

    render() {

        const writeLocal=()=>{
            localStorage.setItem(this.state.nameLocal, "");
        }

        const addTask = () => {
            this.setState({showInput: !this.state.showInput})
        }
        const saveTask = () => {
            let name = document.getElementById("name").value;
            let tasks = this.state.tasks;
            let newTask = {id: tasks.length === 0 ? 1 : tasks[tasks.length - 1].id + 1, name, completed: false}
            tasks.push(newTask)
            this.setState({tasks, showInput: false})
            writeLocal()
        }
        const changeCompleted = (item) => {
          let  tasks=this.state.tasks;
          let index= tasks.indexOf(item);
          tasks[index].completed=!item.completed;
          this.setState({tasks})
            writeLocal()

        }
        return (
            <div>

                <Container style={{background: 'cyan'}}>
                    <Row>
                        <Row>
                            <Col>
                                <h1 class="text-center">Tasklar</h1>
                                <Button color="primary" onClick={addTask}>Add Task</Button>
                            </Col>
                        </Row>
                        {this.state.showInput ?
                            <Row>
                                <Col md='3'>
                                    <Input type="text" name="name" id="name" placeholder="Enter task name"/>
                                    <Button color='success' onClick={saveTask}>Save</Button>
                                    <Button color="danger" onClick={addTask}>Cancel</Button>
                                </Col>
                            </Row>
                            : ""
                        }
                        <Col md="2">
                            <Row>
                                <Col>
                                    Completed
                                </Col>
                            </Row>
                            <Row>
                                <Col>
                                   <h1> {this.state.tasks.filter(value=>value.completed).length}</h1>
                                </Col>
                            </Row>
                        </Col>
                        <Col md="2">
                            <Row>
                                <Col>
                                    Progress
                                </Col>
                            </Row>
                            <Row>
                                <Col>
                                    <h1>{this.state.tasks.filter(value => !value.completed).length}</h1>
                                </Col>
                            </Row>
                        </Col>
                        <Col md="2">
                            <Row>
                                <Col>
                                    All
                                </Col>
                            </Row>
                            <Row>
                                <h1>{this.state.tasks.length}</h1>
                            </Row>
                        </Col>
                    </Row>


                    {this.state.tasks.map(bittaTask =>

                        <Col md={{size: 4, offset: 2}}>
                            <TodoItem ketmon={bittaTask} changeCompleted={changeCompleted}/>
                        </Col>)}

                </Container>
            </div>
        );
    }
}

Todo.propTypes = {};

export default Todo;
