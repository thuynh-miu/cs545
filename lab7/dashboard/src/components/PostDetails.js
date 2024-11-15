import { useState, useEffect } from "react"
import { fetchService } from "../services/fetchServices"
import Comments from "./Comments"
import './PostDetails.css'

const PostDetails = (props) => {
    const [postDetail, setPostDetail] = useState({
        comments: []
    })

    const fetchPost = async () => {
        if (!props.id) return;

        try {
            const post = await fetchService.getById("posts", props.id)
            if (post) {
                setPostDetail(post)
            }
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        fetchPost()
    }, [props.id])

    return (
        <div className="postDetails">
            <div className="postDetailsTitle">
                <p className="postDetailTitleText">{postDetail.title}</p>
                <p>{postDetail.author}</p>
            </div>
            <div className="postDetailsContent">
                <p>{postDetail.content}</p>
            </div>

            <Comments comments={postDetail.comments} />

            <div className="postDetailsButton">
                <button className="buttonUnderline" type="submit">{'Edit'}</button>
                <button className="buttonUnderline" type="submit">{'Delete'}</button>
            </div>
        </div>
    )
}

const styles = {
    postDetails: {
        marginTop: '20px',
        width: '600px',
        border: '2px solid #385d8a',
        padding: '10px',
    },
    postDetailsTitle: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    postDetailsContent: {
        display: 'flex',
        flexDirection: 'column'
    },
    postDetailsButton: {
        display: 'flex',
        justifyContent: 'center',
        marginTop: '40px'
    },
    buttonUnderline: {
        border: 'none',
        backgroundColor: 'transparent',
        textDecoration: 'underline',
        marginRight: '10px',
        fontSize: '16px',
        color: 'red',
        cursor: 'pointer',
        margin: '5px 20px'
    }
}

export default PostDetails