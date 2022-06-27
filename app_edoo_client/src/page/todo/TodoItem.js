import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Button, Card, CardText, CardTitle, Input, Label} from "reactstrap";

class TodoItem extends Component {
    render() {
        const {ketmon, changeCompleted}=this.props
        return (
            <div>

                <Card body inverse style={{backgroundColor: `${ketmon.completed?"green":"red"}`}}>
                    <CardTitle tag="h5">
                        <Label>
                            {ketmon.name}
                        <Input  type="checkbox" onClick={()=>changeCompleted(ketmon)} />
                        </Label>
                    </CardTitle>
                </Card>
            </div>
        );
    }
}

TodoItem.propTypes = {};

export default TodoItem;
