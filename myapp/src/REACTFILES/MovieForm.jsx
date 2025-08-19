import React, { Component } from "react";

class MovieForm extends Component {
  constructor(props) {
    super(props);

    // state -> to store form fields and submitted movies
    this.state = {
      title: "",
      director: "",
      year: "",
      genre: "Action",
      rating: "",
      description: "",
      platforms: [], // multiple checkboxes
      movies: [] // list of submitted movies
    };

    console.log("constructor executed"); // lifecycle method log
  }

  // lifecycle method - called before render, when props change
  static getDerivedStateFromProps(props, state) {
    console.log("getDerivedStateFromProps executed");
    return null; // we don’t update state from props in this case
  }

  // lifecycle method - called only once after component first renders
  componentDidMount() {
    console.log("componentDidMount executed");
  }

  // lifecycle method - called before re-render (e.g. state/props change)
  shouldComponentUpdate(nextProps, nextState) {
    console.log("shouldComponentUpdate executed");
    return true; // return false to stop re-render
  }

  // lifecycle method - called just before DOM update
  getSnapshotBeforeUpdate(prevProps, prevState) {
    console.log("getSnapshotBeforeUpdate executed");
    return null;
  }

  // lifecycle method - called after update
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log("componentDidUpdate executed");
  }

  // lifecycle method - called before component is removed
  componentWillUnmount() {
    console.log("componentWillUnmount executed");
  }

  // Handle input changes (text, number, textarea, select)
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  // Handle checkbox changes (streaming platforms)
  handleCheckboxChange = (event) => {
    const { value, checked } = event.target;
    if (checked) {
      this.setState((prevState) => ({
        platforms: [...prevState.platforms, value]
      }));
    } else {
      this.setState((prevState) => ({
        platforms: prevState.platforms.filter((p) => p !== value)
      }));
    }
  };

  // Handle form submission
  handleSubmit = (event) => {
    event.preventDefault();

    // new movie object
    const newMovie = {
      title: this.state.title,
      director: this.state.director,
      year: this.state.year,
      genre: this.state.genre,
      rating: this.state.rating,
      description: this.state.description,
      platforms: this.state.platforms
    };

    // update movies list
    this.setState((prevState) => ({
      movies: [...prevState.movies, newMovie],

      // reset form fields
      title: "",
      director: "",
      year: "",
      genre: "Action",
      rating: "",
      description: "",
      platforms: []
    }));
  };

  // render method - always required in class components
  render() {
    console.log("render executed");

    return (
      <div className="container mt-4">
        <div className="card p-4 shadow">
          <h2 className="mb-3">Add Movie</h2>

          {/* Form */}
          <form onSubmit={this.handleSubmit}>
            <div className="mb-3">
              <label>Movie Title</label>
              <input
                type="text"
                name="title"
                className="form-control"
                value={this.state.title}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label>Director</label>
              <input
                type="text"
                name="director"
                className="form-control"
                value={this.state.director}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label>Release Year</label>
              <input
                type="number"
                name="year"
                className="form-control"
                value={this.state.year}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label>Genre</label>
              <select
                name="genre"
                className="form-control"
                value={this.state.genre}
                onChange={this.handleChange}
              >
                <option>Action</option>
                <option>Comedy</option>
                <option>Drama</option>
                <option>Sci-Fi</option>
                <option>Horror</option>
              </select>
            </div>

            <div className="mb-3">
              <label>Rating</label>
              <br />
              {[1, 2, 3, 4, 5].map((num) => (
                <label key={num} className="me-2">
                  <input
                    type="radio"
                    name="rating"
                    value={num}
                    checked={this.state.rating === String(num)}
                    onChange={this.handleChange}
                  />{" "}
                  {num}
                </label>
              ))}
            </div>

            <div className="mb-3">
              <label>Description</label>
              <textarea
                name="description"
                className="form-control"
                value={this.state.description}
                onChange={this.handleChange}
              ></textarea>
            </div>

            <div className="mb-3">
              <label>Available on Streaming Platforms</label>
              <br />
              {["Netflix", "Amazon Prime", "Disney+", "Others"].map(
                (platform) => (
                  <label key={platform} className="me-3">
                    <input
                      type="checkbox"
                      value={platform}
                      checked={this.state.platforms.includes(platform)}
                      onChange={this.handleCheckboxChange}
                    />{" "}
                    {platform}
                  </label>
                )
              )}
            </div>

            <button type="submit" className="btn btn-primary">
              Add Movie
            </button>
          </form>
        </div>

        {/* Show Submitted Movies */}
        {this.state.movies.length > 0 && (
          <div className="mt-4">
            <h3>Movie List</h3>
            <table className="table table-bordered">
              <thead>
                <tr>
                  <th>Title</th>
                  <th>Director</th>
                  <th>Release Year</th>
                  <th>Genre</th>
                  <th>Rating</th>
                  <th>Available on Streaming Platforms</th>
                </tr>
              </thead>
              <tbody>
                {this.state.movies.map((movie, index) => (
                  <tr key={index}>
                    <td>{movie.title}</td>
                    <td>{movie.director}</td>
                    <td>{movie.year}</td>
                    <td>{movie.genre}</td>
                    <td>{movie.rating}</td>
                    <td>{movie.platforms.join(", ")}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    );
  }
}

export default MovieForm;
